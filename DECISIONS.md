## Rule Representation
Rules are chiefly represented via a config.json file. In a production environment this would be represented in AppConfig (or similar) - something that allows production teams to change rules on the fly, adding and subtracting as-needed. We don't want to have to wait for a full code deployment or directly read code to modify rules. The rules use [SpEL](https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/expressions.html) to define their behavior, which allows us to retain the ability to define our own behavior (which we did with `requires`, a keyword which performs a not-null check on a field.

Two rules are defined in-code: if married, then spouse info, and if dependent, then have parent income. Notionally, it should be possible to use SpEL to represent them (e.g. a ternary with our defined keyword), but I encountered problems in testing and had to pivot for time reasons. I remain convinced it is possible to represent these rules with our current setup, I just don't have the time to fix it. I'd cut a tech debt ticket here.

## Error Handling
This is a production system returning data that will in some way be surfaced to a member of the public. It would be a terrible experience to submit an application, experience a failure, fix it, and submit again only to find there was another failure you weren't made aware of. We run all rules and return all failures.

## Rule Priority and Severity
Rules have a severity/priority field, although currently this does nothing. I made the assumption that if any information violates a rule, that we cannot process the application, and thus severity doesn't matter - everything is fatal. We could introduce behavior to change this (and it might be desirable to do so - is there information a FAFSA can be processed without, but is still worth flagging?) by adding a warnings field to our output and putting anything under a certain severity in warnings. We could also use the severity to adjust the order of rules, but because all rules are fatal, I didn't see a reason to adjust order from just what's written in the config file, top to bottom.

## Rule Conflicts
Identifying rule conflicts in the first place requires building an additional logic integrity system that I didn't have time to create. At present time this will have to be handled through operational best practice - rules reviewed by the team. We should cut a design ticket to implement this conflict-identifying system.

## Performance
We are applying all rules to all applications, which has some performance considerations at scale - we are as performant as possible with this approach, though: one iteration over every rule for every application. Introducing a severity system or some kind of cutoff (e.g. "Run critical failure rules first then, only if everything is green, run the warnings") could help alleviate this. We have cut out a significant amount of work by only making one iteration over the rules file itself at startup, which cuts out potentially expensive String parsing operations, but it comes at the cost of less reactivity to config changes. We'd have to introduce some kind of life cycle on whatever compute is running these so they can refresh their rules every so often.

We could take an alternate approach of running over the rules file every time someone makes a request to our system, giving us perfect reactivity, but it comes at the cost of ingesting the whole rules file every time we run.

## Extensibility
The sky is the limit. We're using the flexible SpEL framework, which is already feature-rich (math operations, null checking, arbitrary regex matching, ternary logic, and more) and allows us to extend anything we want with our own keywords.
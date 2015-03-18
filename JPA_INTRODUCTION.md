# Introduction #
BeanManager was born a long time ago on top of  java 1.3 before the introduction of annotations and at that time the only "standard" mechanism to achieve meta-information was bean info.

I felt the necessity to do a refactoring to introduce support to annotations and the introduction of JPA standard gave me the occasion to do this job

Java Persistence API (JPA) is an emerging standard that is spreading rapidly so the its introduction in BeanManager framework will help the developers to deal with it

## Assumptions ##

It will keep full compatibility with traditional bean-manager approach and will be possible mix in the same application JPA way with the traditional one.

## Roadmap ##

In the first step (1.4.x releases) this introduction will concern  use of a subset of JPA annotations that best fit with Bean Manager approach

In the second step (1.5.x releases) i'll introduce use of persistence manager
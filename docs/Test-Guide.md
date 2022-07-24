---
title: Test Guide
---

## Writing good tests
1. Readable
    * Descriptive names, short name of mathods, Given/When/Then Structure
2. Reliable
    * Use UI as little as possible (use the API)
    * Fix flakes when they occur
    * Write independent tests
    * Reliable UI tests are hard to write, it also might find issue in the application that should
      be fixed. Even they are not yet seen in production
3. Rapid
    * total time should take < 15 mins
4. Relevant
    * high priority
    * Is it integration_test?
    * Can it be tested at a lower level?
    * Is it already tests as a part of another suite?


### Readable

Tests should be first readable. They act as documentation of the behaviour of the system.
If this is compromised and/or if tests are hard to understand by anyone in the Engineering team
then this compromise their value.

### Structure

Use Given/When/Then in each test to give the test a clear structure.

### Descriptive Test Names

It should be possible with limited product knowledge to understand what a test is doing by simply
reading its name. This can be difficult,  ut it is well worth the effort.

If it's "impossible" to do this for your test , consider to split your tests in small separate tests.

### Reliable

Automated tests need to be reliable, so they don't slow down development or worse get ignored and
loose their value at all.

Reliability can be improved by doing as little as practical via the UI.

Ensure your test is independent (it doesn't use shared global state) and do not rely on each other.

### Rapid

Automated tests should be also fast as to provide quick feedback on state of change (application
or test code)

Speed can be improved by doing:
1. running tests in parallel;
2. rationally using API instead of browser (i.e if same step was automated in UI,
   same functionality can be put in API);
3. Run tests against multiple instances;

### Relevant

Automated test are valuable when they provide feedback on changes to enable safer and faster releases.

If test failed and highlighted defect, the test has a value.
There is also value when test is passed, it also gives confidence.

Tests are less relevant when their functionality is:
* less valuable (e.g. feature that nobody uses);
* less likely to fail;
* already well-covered by other tests;

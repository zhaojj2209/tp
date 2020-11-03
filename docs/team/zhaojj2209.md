---
layout: page
title: Zhao Jingjing's Project Portfolio Page
---

## Project: Fine$$e

### Overview

Fine$$e is a desktop finance tracker that allows tertiary students to better manage their finances, cultivating good financial habits such as saving. It is optimised for CLI users so that expenses and income can be tracked efficiently by typing in commands. It is written in Java, and has a GUI created with JavaFX.

### Summary of Contributions

* **New feature**: Added the monthly budget feature to the finance tracker.
  * What it does: With the addition of the monthly budget, the finance tracker allows the user to set their desired monthly expense limit and monthly savings goal, and calculates the user's remaining budget and current savings.
  * Justification: This feature significantly enhances the capabilities of the finance tracker by allowing the user to track their progress in their savings, and paves the way for the application's analytics feature.
  * Highlights: This feature required heavy use of the observer design pattern as all values in the monthly budget have to be observed by the UI so that the values and calculations can be updated immediately upon any changes.

* **New feature**: Revamped the finance tracker's `find` command.
  * What it does: Instead of only title keywords, the user can now search for transactions in the finance tracker by title keyphrase, amount, date, category, amount range and date range.
  * Justification: This feature significantly increases the user's ease in finding the transactions they have entered into the finance tracker.
  * Highlights: This feature involved changes in the way the find command is parsed, and different predicates for each of the search scenarios were created.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=w16&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=zhaojj2209&tabRepo=AY2021S1-CS2103T-W16-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Created labels for issues and pull requests on GitHub.
  * Managed release `v1.3` (1 release) on GitHub.

* **Enhancements to existing features**:
  * Removed duplicate checks to allow duplicate transactions to be added to the finance tracker. (Pull request #72)
  * Restricted command inputs to allow for only one title/amount/date input in the finance tracker. (Pull request #201)

* **Documentation**:
  * User Guide:
    * Updated user guide to reflect commands for v1.2. (Pull request #114)
    * Updated user guide for `find`, `set-expense-limit` and `set-savings-goal` commands for v1.3. (Pull request #176)
  * Developer Guide:
    * Updated user stories and use cases in the developer guide. (Pull request #13)
    * Updated find transactions and monthly budget features in the developer guide. (Pull requests #120, #144)

* **Community**:
  * PRs reviewed (with non-trivial review comments): #39, #48, #90, #107, #132.



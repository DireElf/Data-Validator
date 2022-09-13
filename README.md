### Hexlet tests and linter status:
[![Actions Status](https://github.com/DireElf/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/DireElf/java-project-78/actions)
[![Build](https://github.com/DireElf/java-project-78/actions/workflows/build.yml/badge.svg)](https://github.com/DireElf/java-project-78/actions/workflows/build.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/150402524a7c3b3a6988/maintainability)](https://codeclimate.com/github/DireElf/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/150402524a7c3b3a6988/test_coverage)](https://codeclimate.com/github/DireElf/java-project-78/test_coverage)

The project is designed to validate data by setting certain conditions. 

The current build includes string validation (content, minimum length, presence of given substrings), number validation (numeric type, number sign, presence in a given range), and map validation (map data type, matching a specified size and/or validation scheme). Validation of any eight-bit numbers is available, including floating point numbers.

Here are some examples:

```java
Validator v = new Validator();

StringSchema schema = v.string();

schema.isValid(""); // true
schema.isValid(null); // true

schema.required();

schema.isValid("what does the fox say"); // true
schema.isValid("hexlet"); // true
schema.isValid(null); // false
schema.isValid(""); // false

schema.contains("wh").isValid("what does the fox say"); // true
schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false

schema.isValid("what does the fox say"); // false
```

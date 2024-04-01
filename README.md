# ReqRes API Test Manual and Automation

This repository contains comprehensive manual and automated tests for the ReqRes API using a combination of tools including Postman, Newman, Java, REST Assured, and Allure Report.

## Overview

ReqRes API is a mock API service commonly used for testing and development purposes. This repository provides a detailed guide on how to test the ReqRes API manually using Postman and automate these tests using Java and REST Assured. Additionally, it demonstrates how to generate professional test reports using Allure.

## Manual Testing

Manual testing involves interacting with the ReqRes API endpoints using Postman. This approach allows for detailed examination of API behavior and responses.

## Automation Testing

Automation testing streamlines the testing process by programmatically executing test cases. In this repository, we demonstrate how to automate testing of the ReqRes API using Java and REST Assured.

## Tools Used

- Postman: API development and testing tool.
- Newman: Command-line collection runner for Postman.
- Java: Programming language for automation scripting.
- REST Assured: Java library for testing RESTful APIs.
- Allure: Test reporting tool for generating professional test reports.

## How to Run Automated Tests

1. Clone this repository to your local machine.
2. Ensure you have Java installed on your system.
3. Install Maven if you haven't already.
4. Navigate to the project directory.
5. Run `mvn clean test` to execute the automated tests.
6. After the tests have completed, run `mvn allure:serve` to generate and view the Allure report.

## Structure

- `tests/`: Contains automated test scripts written in Java using REST Assured.
- `postman/`: Contains Postman collections for manual testing.
- `reports/`: Contains generated Allure test reports.

## Contributions

Contributions are welcome! If you have any suggestions, improvements, or bug fixes, feel free to open an issue or submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

Feel free to customize this README according to your project's specific details and requirements.

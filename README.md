# ItemService Interview Exercise

This exercise will help us assess your Java and Spring skills by extending an existing REST API for managing grocery items. You will add support for item categories and sorting.


## Requirements

### 1. Add Category Support

- Add a `category` field to the `Item` class 
- Update sample data in `ItemService` to include categories (e.g., Dairy, Produce, Meat).
- Implement filtering by category in the service layer.
- Expose category filtering via the REST API.

Please assign the following categories to each sample item:

| Item Name         | Description                          | Category  |
|-------------------|--------------------------------------|-----------|
| Milk              | 1 gallon whole milk                  | Dairy     |
| Butter            | Salted butter 16oz                   | Dairy     |
| Cheese            | Cheddar cheese block 8oz             | Dairy     |
| Eggs              | Dozen large eggs                     | Dairy     |
| Bread             | Whole wheat loaf                     | Bakery    |
| Chicken Breast    | Boneless skinless chicken breast/lb  | Meat      |
| Ground Beef       | Lean ground beef/lb                  | Meat      |
| Apples            | Fresh red apples (per lb)            | Produce   |
| Bananas           | Yellow bananas (per lb)              | Produce   |
| Tomatoes          | Fresh Roma tomatoes (per lb)         | Produce   |
| Rice              | Long grain white rice 5lb bag        | Pantry    |
| Pasta             | Spaghetti pasta 16oz                 | Pantry    |

### 2. Enable Sorting

- Add support for sorting items by name, price, or quantity.
- Sorting should be configurable via query parameters (e.g., `sortBy=name`, `sortOrder=asc`).
- Implement sorting logic in the service layer.
- Expose sorting via the REST API.

Example Sorting Parameters

- `sortBy=name` or `sortBy=price` or `sortBy=quantity`
- `sortOrder=asc` or `sortOrder=desc`

### 3. Update or Add Tests

- Update or add tests to verify category filtering and sorting.

### 4. Hints

- Consider edge cases (e.g., invalid category, unsupported sort field).
- Use Java streams and comparators for sorting.
- You may use either a `String` or an `enum` for category.

## Getting Started

- Review the existing codebase, especially `Item`, `ItemService`, and `ItemController`.
- Look for `// TODO` comments in the code for suggested starting points.
- Use Java streams and comparators for sorting.
- Follow best practices for REST API design and error handling.

### Building and running the application
Prerequisites:
- Java 17 or higher installed
- Maven installed

Maven commands
- Build:  mvn clean install 
- Run: mvn spring-boot:run or java -jar target/springboot-interview-api-1.0.0.jar
- Once running, the REST API will be available at:  http://localhost:8080/api/items 
- Test: mvn test


# Submission Instructions

To submit your solution, please follow these steps:

1. **Fork this repository** to your own GitHub account.
2. **Complete the exercise** in your forked repository.  
   - Make sure all your changes are committed with clear, descriptive commit messages.
   - Include any explanations or documentation in your fork (e.g., in a README or as code comments).
3. **Submit your work** by providing a link to your forked repository.
   - You may also open a pull request to this repository if instructed.

**Note:**  
- Ensure your repository is public or accessible so reviewers can view your code.
- If you have questions or need clarification, please reach out before submitting.

---

Thank you for participating!

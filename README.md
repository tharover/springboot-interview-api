# ItemService Interview Exercise

## Overview

This exercise will help us assess your Java and Spring skills by extending an existing REST API for managing grocery items. You will add support for item categories and sorting.

---

## Requirements

### 1. Add Category Support

- Add a `category` field to the `Item` class 
- Update sample data in `ItemService` to include categories (e.g., Dairy, Produce, Meat).
- Implement filtering by category in the service layer.
- Expose category filtering via the REST API.

### 2. Enable Sorting

- Add support for sorting items by name, price, or quantity.
- Sorting should be configurable via query parameters (e.g., `sortBy=name`, `sortOrder=asc`).
- Implement sorting logic in the service layer.
- Expose sorting via the REST API.

### 3. Update or Add Tests

- Update or add tests to verify category filtering and sorting.

---

## Getting Started

- Review the existing codebase, especially `Item`, `ItemService`, and `ItemController`.
- Look for `// TODO` comments in the code for suggested starting points.
- Use Java streams and comparators for sorting.
- Follow best practices for REST API design and error handling.

---

## Submission Guidelines

- Submit your code changes and updated tests.
- Include a brief explanation of your approach and any design decisions.
- If you have questions or need clarification, please ask!

---

### Sample Items and Categories

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

---
## Example Sorting Parameters

- `sortBy=name` or `sortBy=price` or `sortBy=quantity`
- `sortOrder=asc` or `sortOrder=desc`

---

## Hints

- Consider edge cases (e.g., invalid category, unsupported sort field).
- Use Java streams and comparators for sorting.
- You may use either a `String` or an `enum` for category.

---

package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Tests umm3601.todo.Database listUser functionality
 */
public class FullTodoListFromDB {

  @Test
  public void totalTodoCount() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allUsers = db.listTodos();
    assertEquals("Incorrect total number of todos", 10, allUsers.length);
  }

  @Test
  public void firstUserInFullList() throws IOException {
    Database db = new Database("src/main/data/users.json");
    Todo[] allTodos = db.listTodos();
    Todo firstTodo = allTodos[0];
    //TODO: tests for firstTodo
  }
}

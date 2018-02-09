package umm3601.todo;

import org.junit.*;

import javax.xml.crypto.Data;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class sortTodosFromDB {
  Database db;
  Todo[] allTodos;

  @Before
  public void setup()throws IOException{
    db = new Database("src/main/data/todos.json");
    allTodos = db.listTodos();
  }

  @Test
  public void sortTodosByCategory() {
    Todo[] sortedTodoList = db.limitTodos(allTodos, 10);
    sortedTodoList = db.sortTodos(sortedTodoList, "category");

    String[] expected = {"groceries", "homework", "homework", "homework", "homework", "software design", "software design", "software design", "video games", "video games"};

    //make sure each category is as expected
    for (int i = 0; i < 10; i++) {
      assertEquals("Incorrect Category", expected[i], sortedTodoList[i].category);
    }

  }
}

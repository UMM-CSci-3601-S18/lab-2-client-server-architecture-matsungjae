package umm3601.todo;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class GetTodoByIDFromDB {

  //tests getting a specific todo by its ID. It specifically gets the second todo owned by Blanche in the todo file.
  @Test
  public void getTodoByIDFromDB() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo secondBlancheTodo = db.getTodo("58895985186754887e0381f5");

    assertEquals( "Incorrect Owner","Blanche", secondBlancheTodo.owner);
    assertEquals( "Incorrect Status",true, secondBlancheTodo.status);
    assertEquals( "Incorrect body","Incididunt enim ea sit qui esse magna eu. Nisi sunt exercitation est " +
      "Lorem consectetur incididunt cupidatat laboris commodo veniam do ut sint.", secondBlancheTodo.body);
    assertEquals( "Incorrect category","software design", secondBlancheTodo.category);  }
}

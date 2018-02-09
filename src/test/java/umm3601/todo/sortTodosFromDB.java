package umm3601.todo;

import org.junit.*;

import javax.xml.crypto.Data;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class sortTodosFromDB {
  Database db;
  Todo[] sortedTodos;

  @Before
  public void setup()throws IOException{
    db = new Database("src/main/data/todos.json");
    sortedTodos = db.listTodos();
    sortedTodos = db.limitTodos(sortedTodos, 10);
  }

  @Test
  public void sortTodosByCategory() {
    sortedTodos = db.sortTodos(sortedTodos, "category");

    String[] expected = {"groceries", "homework", "homework", "homework", "homework", "software design", "software design", "software design", "video games", "video games"};

    //make sure each category is as expected
    for (int i = 0; i < 10; i++) {
      assertEquals("Incorrect Category", expected[i], sortedTodos[i].category);
    }

  }

  @Test
  public void sortTodosByBody() {
    sortedTodos = db.sortTodos(sortedTodos, "body");

    String[] expected = {"Aliqua esse aliqua veniam id nisi ea. Ullamco Lorem ex aliqua aliquip cupidatat incididunt reprehenderit voluptate ad nisi elit dolore laboris.",
      "Eiusmod commodo officia amet aliquip est ipsum nostrud duis sunt voluptate mollit excepteur. Sunt non in pariatur et culpa est sunt.",
      "In sunt ex non tempor cillum commodo amet incididunt anim qui commodo quis. Cillum non labore ex sint esse.",
      "Incididunt enim ea sit qui esse magna eu. Nisi sunt exercitation est Lorem consectetur incididunt cupidatat laboris commodo veniam do ut sint.",
      "Ipsum esse est ullamco magna tempor anim laborum non officia deserunt veniam commodo. Aute minim incididunt ex commodo.",
      "Laborum incididunt nisi eiusmod aliqua velit quis occaecat excepteur ut in ad. Commodo adipisicing sint ipsum irure amet exercitation voluptate mollit.",
      "Nisi sit non non sunt veniam pariatur. Elit reprehenderit aliqua consectetur est dolor officia et adipisicing elit officia nisi elit enim nisi.",
      "Nostrud ullamco labore exercitation magna. Excepteur aute aliqua veniam veniam nisi eu occaecat ea magna do.",
      "Ullamco irure laborum magna dolor non. Anim occaecat adipisicing cillum eu magna in.",
      "Veniam ut ex sit voluptate Lorem. Laboris ipsum nulla proident aute culpa esse aute pariatur velit deserunt deserunt cillum officia dolore."};

    //make sure each body is as expected
    for (int i = 0; i < 10; i++) {
      assertEquals("Incorrect Body", expected[i], sortedTodos[i].body);
    }

  }

  @Test
  public void sortTodosByOwner() {
    sortedTodos = db.sortTodos(sortedTodos, "owner");

    String[] expected = {"Barry", "Blanche", "Blanche", "Blanche", "Blanche", "Blanche", "Fry", "Fry", "Fry", "Workman"};

    //make sure each owner is as expected
    for (int i = 0; i < 10; i++) {
      assertEquals("Incorrect Owner", expected[i], sortedTodos[i].owner);
    }

  }

  @Test
  public void sortTodosByStatus() {
    sortedTodos = db.sortTodos(sortedTodos, "status");

    Boolean[] expected = {false, false, false, false, true, true, true, true, true, true};
    //make sure each category is as expected
    for (int i = 0; i < 10; i++) {
      assertEquals("Incorrect Status", expected[i], sortedTodos[i].status);
    }

  }
}

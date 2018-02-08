package umm3601.todo;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;

public class Database {
  private Todo[] allTodos;

  public Database(String userDataFile) throws IOException {
    Gson gson = new Gson();
    FileReader reader = new FileReader(userDataFile);
    allTodos = gson.fromJson(reader, Todo[].class);
  }

  public Todo[] listTodos() {
    return allTodos;
  }
}

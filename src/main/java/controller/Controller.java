package controller;

public class Controller {
    private IClient executor;

    public void handleKey(String key) {
        switch (key) {
            case ("W") -> executor.execute(Command.Top);
            case ("A") -> executor.execute(Command.Left);
            case ("S") -> executor.execute(Command.Down);
            case ("D") -> executor.execute(Command.Right);
            case ("Space") -> executor.execute(Command.Rotate);
            case ("Esc") -> executor.execute(Command.Menu);
            case ("Enter") -> executor.execute(Command.Enter);
        }
    }
}

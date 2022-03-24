import Exceptions.LoadConfigurationException;
import view.GUIConfiguration;
import view.TetrisGUI;

public class Main {

    public static void main(String[] args) {

        //  TODO: understand how to find resources properly
        GUIConfiguration guiConfiguration = new GUIConfiguration("src/main/resources/configuration.properties");
        try {
            guiConfiguration.configure();
        } catch (LoadConfigurationException configurationException) {
            configurationException.printStackTrace();
        }

        TetrisGUI app = new TetrisGUI(guiConfiguration);
        app.setVisible(true);
    }
}

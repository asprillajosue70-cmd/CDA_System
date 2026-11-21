import config.AppDependencies;
import views.LoginView;
import javax.swing.SwingUtilities;

public class App {

    public static void main(String[] args) {
        AppDependencies dependencies = new AppDependencies();

        SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView(dependencies);
            loginView.setVisible(true);

            System.out.println("================================");
            System.out.println(" SISTEMA DE GESTION CDA");
            System.out.println("================================");

            System.out.println("\nProyecto iniciado correctamente.");
        });
    }
}

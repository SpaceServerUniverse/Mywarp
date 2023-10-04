package space.yurisi.mywarp.command;

import space.yurisi.mywarp.Mywarp;
import space.yurisi.mywarp.connector.UniverseCoreAPIConnector;

public class MywarpCommandManagaer {

    public MywarpCommandManagaer(Mywarp main) {
        init(main, main.getConnector());
    }

    private void init(Mywarp main, UniverseCoreAPIConnector connector) {
        main.getCommand("mywarp").setExecutor(new MywarpHelpCommand(connector));
        main.getCommand("mwadd").setExecutor(new MywarpCreateCommand(connector));
        main.getCommand("mwdel").setExecutor(new MywarpDeleteCommand(connector));
        main.getCommand("mwlist").setExecutor(new MywarpListCommand(connector));
        main.getCommand("mwtp").setExecutor(new MywarpTeleportCommand(connector));
        main.getCommand("mwvisit").setExecutor(new MywarpVisitCommand(connector));
        main.getCommand("mwvisitlist").setExecutor(new MywarpVisitListCommand(connector));
    }
}


package space.yurisi.mywarp.command;

import space.yurisi.mywarp.Mywarp;

public class MywarpCommandManagaer {

    public MywarpCommandManagaer(Mywarp main) {
        init(main);
    }

    private void init(Mywarp main) {
        main.getCommand("mywarp").setExecutor(new MywarpHelpCommand());
        main.getCommand("mwadd").setExecutor(new MywarpCreateCommand());
        main.getCommand("mwdel").setExecutor(new MywarpDeleteCommand());
        main.getCommand("mwlist").setExecutor(new MywarpListCommand());
        main.getCommand("mwtp").setExecutor(new MywarpTeleportCommand());
        main.getCommand("mwvisit").setExecutor(new MywarpVisitCommand());
    }
}


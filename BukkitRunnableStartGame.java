public static int timer = 121;
public static boolean start = false;

@Override
public void run() {

    if (!(GameStatus.isStatus(GameStatus.ATTENRE))) {
        timer = 121;
        start = false;
        this.cancel();
        return;
    }

    if (Bukkit.getOnlinePlayers().size() < 2) {
        Bukkit.broadcastMessage(ChatColor.RED + "Il n'y a pas assez de joueurs pour demarrer la partie .");
        timer = 121;
        start = false;
        setLevel();
        this.cancel();
        return;
    }

    if (Bukkit.getOnlinePlayers().size() == 4) {
        if (timer > 60) {
            timer = 60;
            setLevel();
            return;
        }
    }

    if (Bukkit.getOnlinePlayers().size() == 10) {
        if(timer > 11) {
            timer = 11;
            setLevel();
            return;
        }
    }

    if (timer == 0) {
        this.cancel();
    }

    timer--;
    setLevel();

    if ((timer == 120) || (timer == 90) || (timer == 60) || (timer == 30) || (timer == 15) || (timer == 10) || (timer <= 5) && (timer != 0)) {
        Bukkit.broadcastMessage(ChatColor.GREEN + "Début de la partie dans " + ChatColor.AQUA + timer + getSecond());
        for(Player players : Bukkit.getOnlinePlayers()) {
            players.playSound(players.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10f, 1f);
        }
    }
}

private static void setLevel() {
    for (Player players : Bukkit.getOnlinePlayers()) {
        players.setLevel(timer);
    }
}

private String getSecond() {
    return timer == 1 ? " seconde" : " secondes";
}

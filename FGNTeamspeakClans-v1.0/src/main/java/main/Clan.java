package main;

class Clan {
    private String name;
    private boolean active;

    public Clan(String name) {
        this.name = name;
        this.active = false;
    }
    public void setActivity(boolean active) {
        this.active = active;
    }

    public String getName() {
        return this.name;
    }

    public boolean getActivity() {
        return this.active;
    }
}

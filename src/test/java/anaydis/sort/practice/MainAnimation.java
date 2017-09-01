package anaydis.sort.practice;

import anaydis.animation.sort.gui.Main;
import anaydis.sort.provider.SorterProviderImpl;

public class MainAnimation {
    public static void main(String[] args) {
        Main.animate(new SorterProviderImpl());
        //Main.race(new SorterProviderImpl());
    }
}

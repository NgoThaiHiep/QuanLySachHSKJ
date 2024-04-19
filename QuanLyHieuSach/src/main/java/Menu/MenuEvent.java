package Menu;

import java.io.IOException;

public interface MenuEvent {
    void selected(int index, int subIndex) throws IOException;
}

package org.example.bo;

import org.example.bo.custom.BOTypes;
import org.example.bo.custom.impl.LoginBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}

    public static BOFactory getInstance() {
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boType) {
        switch (boType) {
            case LOGIN -> {
                return (T) new LoginBOImpl();
            }
        }
        return null;
    }
}

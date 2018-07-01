package com.featuretest;

/**
 * Created by T_DanteZ1 on 26/01/18.
 */

public class DefaultMethodTest {

    public interface TestClient {

        void setX(int x);
        void setY(int y);

        static public int returnValue() {
            return 1;
        }

        default int setZ(int z) {
            return returnValue() + 1;
        }
    }

    public interface AnotherTestClient extends TestClient {

        static public int returnValue() {
            return 2;
        }
    }

    public class SimpleClient implements TestClient {

        @Override
        public void setX(int x) {

        }

        @Override
        public void setY(int y) {

        }
    }

    public class AnotherSimpleClient implements TestClient {

        @Override
        public void setX(int x) {

        }

        @Override
        public void setY(int y) {

        }
    }
}

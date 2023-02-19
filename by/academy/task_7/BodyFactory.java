package by.academy.task_7;

public enum BodyFactory {

    SMALL {
        @Override
        public Body get() {
            return new Body("Маленькое тело");
        }
    },

    MIDDLE {
        @Override
        public Body get() {
            return new Body("Средненькое тело");
        }
    },

    LARGE {
        @Override
        public Body get() {
            return new Body("Большое тело");
        }
    };

    public abstract Body get();
}

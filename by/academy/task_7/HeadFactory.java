package by.academy.task_7;

public enum HeadFactory {

    SMALL {
        @Override
        public Head get() {
            return new Head("Маленькая голова");
        }
    },

    MIDDLE {
        @Override
        public Head get() {
            return new Head("Средненькая голова");
        }
    },

    LARGE {
        @Override
        public Head get() {
            return new Head("Большая голова");
        }
    };

    public abstract Head get();

}

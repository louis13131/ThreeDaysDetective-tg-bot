package ru.urfu;

public enum Day{
    DAY1,
    DAY2,
    DAY3;

    public Day next() {
        Day[] values = Day.values();
        int nextOrdinal = this.ordinal() + 1;

        if (nextOrdinal >= values.length) {
            return this;
        }

        return values[nextOrdinal];
    }

}

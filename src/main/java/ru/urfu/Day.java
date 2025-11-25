package ru.urfu;

public enum Day{
    DAY1,
    DAY2,
    DAY3;

    public Day next() throws Exception {
        Day[] values = Day.values();
        int nextOrdinal = this.ordinal() + 1;

        if (nextOrdinal >= values.length) {
            throw new Exception("Это последний день");
        }

        return values[nextOrdinal];
    }

}
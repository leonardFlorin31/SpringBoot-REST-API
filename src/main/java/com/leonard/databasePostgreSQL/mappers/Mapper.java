package com.leonard.databasePostgreSQL.mappers;

public interface Mapper <A, B> {
    B mapto(A a);

    A mapfrom(B b);


}

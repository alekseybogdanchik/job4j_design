package ru.job4j.isp;


public interface Menu<K extends Comparable<K>> extends Printable, CRUD<K, Action> {

}
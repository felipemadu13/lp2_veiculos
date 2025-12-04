package br.com.autotrack.annotation;

public final class InfoAutorPrinter {

    private InfoAutorPrinter() {}

    public static void printInfoAutor(Class<?> cls) {
        InfoAutor info = cls.getAnnotation(InfoAutor.class);
        if (info != null) {
            System.out.printf("Classe %s - Autor: %s, Data: %s%n",
                cls.getSimpleName(), info.nome(), info.data());
        } else {
            System.out.printf("Classe %s - sem @InfoAutor%n", cls.getSimpleName());
        }
    }
}
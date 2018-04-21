package ru.vise;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс для записи форматированных сообщений в серверный лог [server.log]
 * @author Alien
 *
 */
public class WriteLog {
    protected static Logger logger = Logger.global;

    /**
     * По умолчанию логирование отключено
     */
    private static boolean isStarting = false;

    /**
     * специальный уровень, который может быть использован, чтобы выключать логирование
     */
    public static final Level OFF = Level.OFF;

    /**
     * уровень сообщения, указывающий серьезную ошибку
     */
    public static final Level SEVERE = Level.SEVERE;

    /**
     * уровень сообщения, указывающий потенциальную проблему
     */
    public static final Level WARNING = Level.WARNING;

    /**
     * уровень сообщения для информационных сообщений
     */
    public static final Level INFO = Level.INFO;

    /**
     * уровень сообщения для статических сообщений конфигурации
     */
    public static final Level CONFIG = Level.CONFIG;

    /**
     * уровень сообщения, обеспечивающий трассировку информации
     */
    public static final Level FINE = Level.FINE;

    /**
     * указывает довольно подробную трассировку сообщения
     */
    public static final Level FINER = Level.FINER;

    /**
     * указывает очень подробную трассировку сообщения
     */
    public static final Level FINEST = Level.FINEST;

    /**
     * указывает, что все сообщения будут зарегистрированы
     */
    public static final Level ALL = Level.ALL;

    /**
     * Запись сообщения в [system.log] с типом сообщения по умолчанию INFO
     * @param msg - сообщение, которое записывается в [system.log]
     */
    public static void write(String msg) {
        write(INFO, msg);
    }

    /**
     * Запись сообщения в [system.log] с типом сообщения SEVERE,
     * если вызвано исключение
     * @param ex - исключение
     */
    public static void write(Throwable ex) {
        if (logger == null) {
            logger = Logger.global;
        }

        logger.log(SEVERE, ex.getMessage(), ex);
    }

    /**
     * Запись сообщения в [system.log] с указаным типом сообщения
     * @param level - тип сообщения
     * @param msg - сообщение, которое записывается в [system.log]
     */
    public static void write(Level level, String msg) {
        if (!isStarting() && (!level.equals(SEVERE) || !level.equals(WARNING))) {
            return;
        }

        if (logger == null) {
            logger = Logger.global;
        }

        logger.log(level, msg);
    }

    /**
     * Запускаем логирование, при котором в [server.log] будут
     * записыватся все сообщения
     *
     */
    public static void startLogger() {
        isStarting = true;
    }

    /**
     * Остановка логирования, при котором в [server.log] будут записыватся
     * только те сообщения, которые имеют тип SEVERE или WARNING
     *
     */
    public static void stopLogger() {
        isStarting = false;
    }

    /**
     * Возвращаем состояние логирования
     * @return - результат
     */
    public static boolean isStarting() {
        return isStarting;
    }
}

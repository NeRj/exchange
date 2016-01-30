package nerj.controller.connect;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connect {
    public Connect(){
        Properties props = new Properties();
        try{
            props.load(this.getClass().getResourceAsStream("/conn.properties"));
            Class.forName(props.getProperty("driver")).newInstance();
            conn = DriverManager.getConnection(props.getProperty("host") + "/" + props.getProperty("database"), props);
            logger.info("Успешное подключение к БД");
        } catch (IOException ioEx){
            JOptionPane.showMessageDialog(null,"Ошибка доступа к файлу настроек сервера. Программа будет завершена");
            logger.error("Ошибка доступа к файлу настроек сервера. Программа будет завершена");
            System.exit(0);
        } catch (ClassNotFoundException clEx){
            JOptionPane.showMessageDialog(null,"Ошибка доступа к серверу. Программа будет завершена");
            logger.error("Ошибка доступа к серверу. Программа будет завершена");
            System.exit(0);
        } catch (IllegalAccessException accEx){
            JOptionPane.showMessageDialog(null,"Ошибка доступа к базе данных. Программа будет завершена");
            logger.error("Ошибка доступа к базе данных. Программа будет завершена");
            System.exit(0);
        } catch (InstantiationException instEx){
            JOptionPane.showMessageDialog(null,"Ошибка доступа к серверу. Программа будет завершена");
            logger.error("Ошибка доступа к серверу. Программа будет завершена");
            System.exit(0);
        } catch (SQLException sqlEx){
            JOptionPane.showMessageDialog(null,"База данных не найдена. Программа будет завершена");
            logger.error("База данных не найдена. Программа будет завершена");
            System.exit(0);
        }
    }

    public Connection getConnection() {
        return conn;
    }

    private Connection conn;

    private Logger logger = Logger.getLogger(this.getClass());
}

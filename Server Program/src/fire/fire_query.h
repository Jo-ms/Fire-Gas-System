#ifdef __cplusplus
extern "C" {
#endif

#define QUERY_CREATE_TABLE_SENSOR_VALUE "CREATE TABLE IF NOT EXISTS sensorvalue (id INT AUTO_INCREMENT PRIMARY KEY, date DATE, time TIME, fire INT, gas INT)"
#define QUERY_CREATE_TABLE_SENSOR_CHECK "CREATE TABLE IF NOT EXISTS sensorcheck (id INT AUTO_INCREMENT PRIMARY KEY, date DATE, time TIME, fire INT, gas INT)"
#define QUERY_CREATE_TABLE_ACTURATOR_VALUE "CREATE TABLE IF NOT EXISTS actuoperate (id INT AUTO_INCREMENT PRIMARY KEY, date DATE, time TIME, pump INT, buzzer INT, fan INT, rgbled INT, sensor INT)"
#define QUERY_CREATE_TABLE_ACTURATOR_CHECK "CREATE TABLE IF NOT EXISTS actucheck (id INT AUTO_INCREMENT PRIMARY KEY, date DATE, time TIME, pump INT, buzzer INT, fan INT, rgbled INT)"
#define QUERY_CREATE_TABLE_SETTING "CREATE TABLE IF NOT EXISTS setting (id INT AUTO_INCREMENT PRIMARY KEY, emergency VARCHAR(20))"


#define QUERY_SELECT_SENSOR_DATA "SELECT date, time, fire, gas FROM sensorvalue WHERE time >= DATE_ADD(NOW(), INTERVAL-1 HOUR)"
#define QUERY_INSERT_SENSOR_DATA "INSERT INTO  sensorvalue values (null ,now(), now(), %d, %d)"
#define QUERY_INSERT_SENSOR_CHECK "INSERT into sensorcheck values (null, now(), now(), %d, %d)"
#define QUERY_INSERT_ACTUATOR_VALUE "INSERT into actuoperate values (null, now(), now(), %d, %d, %d, %d, %d)"
#define QUERY_INSERT_ACTUATOR_CHECK "INSERT into actucheck values (null, now(), now(), %d, %d, %d, %d)"

#define QUERY_INSERT_SETTING "INSERT into setting values (null, %s)"
#define QUERY_UPDATE_SETTING "UPDATE setting SET emergency=%s WHERE id = 1"
#define QUERY_SELECT_SETTING "SELECT emergency from setting WHERE id =1"

#define QUERY_SELECT_COUNT_SETTING "SELECT COUNT(*) FROM setting"

#ifdef __cplusplus
}
#endif

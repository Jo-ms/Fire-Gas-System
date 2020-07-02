#ifndef _fire_sensor_H_
#define _fire_sensor_H_
#ifdef __cplusplus
extern "C" {
#endif
#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <sys/types.h>
#include <unistd.h>
#include <errno.h>
#include <wiringPi.h>

#define MAXTIMINGS 85
#define PUMP	21 // BCM_GPIO 5
#define FAN	22 // BCM_GPIO 6
#define RGBLEDPOWER  24 //BCM_GPIO 19
#define BUZZER 25

#define RED	27
#define GREEN	28
#define BLUE	29
#define FLAME_IN 2
#define GAS_IN 0

	extern int wiringPicheck();
	extern int get_fire_sensor(void);
	extern int get_gas_sensor(void);

	
	extern void waterOn();
	extern void waterOff();

	extern void fanOn();
	extern void fanOff();

	extern void buzzerOn();
	extern void buzzerOff();

	extern void rgbledOn();
	extern void rgbledOff();

	extern int get_pump_functionality();
	extern int get_buzzer_functionality();
	extern int get_fan_functionality();
	extern int get_rgbled_functionality();

	extern int act_waterpump_on(const int _use);
	extern int act_fan_on(const int _use);
	extern int act_buzzer_on(const int _use);
	extern int act_rgbled_on(const int _use);


#ifdef __cplusplus
}
#endif
#endif
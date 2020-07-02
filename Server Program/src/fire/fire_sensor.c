#include "fire_sensor.h"


int wiringPicheck(void)
{
	if (wiringPiSetup() == -1)
	{
		fprintf(stdout, "Unable to start wiringPi: %s\n", strerror(errno));
		return 1;
	}
}

int get_fire_sensor()
{
	printf("get fire sensor \n");
	return 0;
}

int get_gas_sensor()
{
	return 0;
}

void waterOn()
{
	pinMode(PUMP, OUTPUT);

	printf("waterpump ON \n");

	digitalWrite(PUMP, 1);
}

void waterOff()
{
	pinMode(PUMP, OUTPUT);

	printf("waterpump OFF \n");

	digitalWrite(PUMP, 0);
}

void fanOn()
{
	pinMode(FAN, OUTPUT);

	printf("waterpump ON \n");

	digitalWrite(FAN, 1);
}

void fanOff()
{
	pinMode(FAN, OUTPUT);

	printf("waterpump OFF \n");

	digitalWrite(FAN, 0);
}

void buzzerOn()
{
	pinMode(BUZZER, OUTPUT);

	printf("waterpump ON \n");

	digitalWrite(BUZZER, 1);
}
void buzzerOff()
{
	pinMode(BUZZER, OUTPUT);

	printf("waterpump OFF \n");

	digitalWrite(BUZZER, 0);
}

void rgbledOn()
{
	pinMode(RED, OUTPUT);
	pinMode(GREEN, OUTPUT);
	pinMode(BLUE, OUTPUT);

	printf("rgbled() \n");

	digitalWrite(RED, 1);
}

void rgbledOff()
{
	pinMode(RED, OUTPUT);
	pinMode(GREEN, OUTPUT);
	pinMode(BLUE, OUTPUT);

	digitalWrite(RED, 0);
	digitalWrite(BLUE, 0);
	digitalWrite(GREEN, 0);
}

int get_pump_functionality()
{
	return 1;
}

int get_buzzer_functionality()
{
	return 1;
}

int get_fan_functionality()
{
	return 1;
}

int get_rgbled_functionality()
{
	return 1;
}

int set_waterpump_active(const int _use)
{
	if (wiringPicheck())
		printf("%s Fail (param :%d) \n", _use);

	pinMode(PUMP, OUTPUT);
	digitalWrite(PUMP, _use);

}

int set_fan_active(const int _use)
{
	if (wiringPicheck())
		printf("%s Fail (param :%d) \n", _use);

	pinMode(FAN, OUTPUT);
	digitalWrite(FAN, _use);

}

int set_buzzer_active(const int _use)
{
	if (wiringPicheck())
		printf("%s Fail (param :%d) \n", _use);

	pinMode(BUZZER, OUTPUT);
	digitalWrite(BUZZER, _use);
}

int set_rgbled_active(const int _use)
{
	if (wiringPicheck())
		printf("%s Fail (param :%d) \n", _use);

	pinMode(RGBLEDPOWER, OUTPUT);
	pinMode(RED, OUTPUT);
	pinMode(GREEN, OUTPUT);
	pinMode(BLUE, OUTPUT);

	digitalWrite(RGBLEDPOWER, _use);
	digitalWrite(RED, _use);
}



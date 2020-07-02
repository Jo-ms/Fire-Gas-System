#ifndef _parser_H_
#define _parser_H_
#include "types.h"

extern int parse_data(char * _parameter, Data * _data);
extern int parse_data_str(char * _parameter, DataSTR * _data);
extern int parse_message(char * data, Message * msg);

#endif
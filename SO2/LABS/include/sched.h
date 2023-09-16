/*
 * sched.h - Estructures i macros pel tractament de processos
 */

#ifndef __SCHED_H__
#define __SCHED_H__

#include <list.h>
#include <types.h>
#include <mm_address.h>
#include <stats.h>
#include <utils.h>

#include <cirBuffer.h>

#define NR_TASKS      15
#define KERNEL_STACK_SIZE	1024

enum state_t { ST_RUN, ST_READY, ST_BLOCKED };

struct task_struct {
	int PID;			/* Process ID. This MUST be the first field of the struct. */
	page_table_entry * dir_pages_baseAddr;
	char * kernel_esp;
	struct list_head list;
	enum state_t estado;
	struct stats estadisticas;
	int n_directorio;
	int sem_ret;
	int quantum;
	int readKeys, requiredKeys;
	unsigned long processBreak;
};

union task_union {
  struct task_struct task;
  unsigned long stack[KERNEL_STACK_SIZE];    /* pila de sistema, per procés */
};

extern union task_union task[NR_TASKS]; /* Vector de tasques */
extern struct list_head keyboardqueue;
int refs_DIR[NR_TASKS];

void writeMsr();


#define KERNEL_ESP(t)       (DWord) &(t)->stack[KERNEL_STACK_SIZE]

#define INITIAL_ESP       	KERNEL_ESP(&task[1])

/* Inicialitza les dades del proces inicial */
void init_task1(void);

void init_idle(void);

void init_sched(void);

struct task_struct * current();

void task_switch(union task_union*t);
void inner_task_switch(union task_union*t);

struct task_struct *list_head_to_task_struct(struct list_head *l);

int allocate_DIR(struct task_struct *t);
int get_DIR_position(struct task_struct *t);

page_table_entry * get_PT (struct task_struct *t) ;

page_table_entry * get_DIR (struct task_struct *t) ;

int get_quantum(struct task_struct *t);
void set_quantum(struct task_struct *t, int new_quantum);

/* Headers for the scheduling policy */
void sched_next_rr();
void update_process_state_rr(struct task_struct *t, struct list_head *dest);
int needs_sched_rr();
void update_sched_data_rr();

void schedule();

void init_stats(struct stats *st);
#endif  /* __SCHED_H__ */
create table student (
  student_id int,
  student_name varchar(255)
);



select * from student;

insert into student values (1, 'Mathew');

insert into student values (2, 'Charles');

select s.student_name  from student s where s.student_id = 1;

drop table student;

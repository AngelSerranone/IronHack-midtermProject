drop schema if exists midtermproject_test;
create schema midtermproject_test;
use midtermproject_test;

DELETE FROM operation WHERE id>=1; 
DELETE FROM checking_acc WHERE id>=1; 
DELETE FROM student_checking_acc WHERE id>=1;
DELETE FROM savings_acc WHERE id>=1; 
DELETE FROM credit_card_acc WHERE id>=1; 
DELETE FROM account WHERE id>=1;
DELETE FROM account_holder WHERE id>=1; 
DELETE FROM third_party WHERE id>=1; 
DELETE FROM admin WHERE id>=1; 
DELETE FROM role WHERE id>=1;
DELETE FROM user WHERE id>=1;


-- 환자 정보
INSERT INTO patient(id, birthday, first_visit, last_visit, memo, name, chart_id, phone, sex)
VALUES(1, '1957-09-14', '1987-01-01', '2022-04-01', '원장님', '최규선', 1, '010-8654-9887', '남자');
INSERT INTO patient(id, birthday, first_visit, last_visit, memo, name, chart_id, phone, sex)
VALUES(2, '1963-09-15', '1988-01-01', '2022-03-31', '원장님 와이프', '김찬경', 2, '010-4423-6341', '여자');
INSERT INTO patient(id, birthday, first_visit, last_visit, memo, name, chart_id, phone, sex)
VALUES(3, '1989-08-25', '1989-08-26', '2020-12-31', '원장님 큰딸', '최지윤', 3, '010-6310-2179', '여자');
INSERT INTO patient(id, birthday, first_visit, last_visit, memo, name, chart_id, phone, sex)
VALUES(4, '1991-11-09', '1991-11-10', '2022-03-01', '원장님 작은딸', '최희윤', 4, '010-9062-3490', '여자');
INSERT INTO patient(id, birthday, first_visit, last_visit, name, chart_id, phone, sex)
VALUES(5, '1995-09-14', '1988-01-01', '2022-04-01', '어나미너스', 5, '010-1234-5678', '남자');
INSERT INTO patient(id, birthday, first_visit, last_visit, name, chart_id, phone, sex)
VALUES(6, '1800-01-01', '1988-01-01', '2022-04-01', '홍길동', 6, '010-1234-5678', '남자');
INSERT INTO patient(id, birthday, first_visit, last_visit, name, chart_id, phone, sex)
VALUES(7, '1800-01-01', '1988-01-01', '2022-04-01', '임꺽정', 7, '010-1234-5678', '남자');

-- 매출 정보
INSERT INTO income(id, amount, date, is_acupuncture, is_cash, is_pay, memo, symptom, patient_id)
VALUES(1, 1000, '2022-04-07', 1, 1, 1, '지인 소개', '발목 염좌', 6);
INSERT INTO income(id, amount, date, is_acupuncture, is_cash, is_pay, symptom, patient_id)
VALUES(2, 7000, '2022-04-07', 1, 1, 1, '발목 염좌', 4);
INSERT INTO income(id, amount, date, is_acupuncture, is_cash, is_pay, memo, symptom, patient_id)
VALUES(3, 7000, '2022-04-07', 1, 1, 1, '지인 소개', '발목 염좌', 5);
INSERT INTO income(id, amount, date, is_acupuncture, is_cash, is_pay, memo, symptom, patient_id)
VALUES(4, 300000, '2022-04-07', 0, 1, 1, '지인 소개', '다이어트 한약', 4);
INSERT INTO income(id, amount, date, is_acupuncture, is_cash, is_pay, memo, symptom, patient_id)
VALUES(5, 1000000, '2022-04-07', 0, 1, 1, '지인 소개', '공진단', 7);

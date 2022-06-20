-- 환자 정보
INSERT INTO patient(id, birthday, first_visit, last_visit, memo, name, chart_id, phone, sex)
VALUES(1, '1957-09-14', '1987-01-01', '2022-04-01', '원장님', '최규선', 1, '01086549887', '남자');
INSERT INTO patient(id, birthday, first_visit, last_visit, memo, name, chart_id, phone, sex)
VALUES(2, '1963-09-15', '1988-01-01', '2022-03-31', '원장님 와이프', '김찬경', 2, '01044236341', '여자');
INSERT INTO patient(id, birthday, first_visit, last_visit, memo, name, chart_id, phone, sex)
VALUES(3, '1989-08-25', '1989-08-26', '2020-12-31', '원장님 큰딸', '최지윤', 3, '01063102179', '여자');
INSERT INTO patient(id, birthday, first_visit, last_visit, memo, name, chart_id, phone, sex)
VALUES(4, '1991-11-09', '1991-11-10', '2022-03-01', '원장님 작은딸', '최희윤', 4, '01090623490', '여자');
INSERT INTO patient(id, birthday, first_visit, last_visit, name, chart_id, phone, sex)
VALUES(5, '1995-09-14', '1988-01-01', '2022-04-01', '어나미너스', 5, '01012345678', '남자');
INSERT INTO patient(id, birthday, first_visit, last_visit, name, chart_id, phone, sex)
VALUES(6, '1800-01-01', '1988-01-01', '2022-04-01', '홍길동', 6, '01012345678', '남자');
INSERT INTO patient(id, birthday, first_visit, last_visit, name, chart_id, phone, sex)
VALUES(7, '1800-01-01', '1988-01-01', '2022-04-01', '임꺽정', 7, '01012345678', '남자');
INSERT INTO patient(id, birthday, first_visit, last_visit, memo, name, chart_id, phone, sex)
VALUES(8, '1992-01-27', '1999-10-10', '2022-03-01', '원장님 작은딸 친구', '이희윤', 8, '01063889771', '여자');

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
INSERT INTO income(id, amount, date, is_acupuncture, is_cash, is_pay, memo, symptom, patient_id)
VALUES(6, 1000000, '2022-04-07', 0, 1, 1, '지인 소개', '공진단', 6);

-- 거래처 정보
INSERT INTO vendor(id, company_name, manager, phone)
VALUES(1, '스포츠투데이', '-', '01000000000');

-- 매입 정보
INSERT INTO outcome(amount, date, item, vendor_id)
VALUES(30000, '2022-04-07', '신문값', 1);
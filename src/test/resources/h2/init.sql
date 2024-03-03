DROP TABLE IF EXISTS race;
DROP TABLE IF EXISTS profession;
DROP TABLE IF EXISTS player;

Create table race
(
    id bigint auto_increment PRIMARY KEY,
    name VARCHAR
);

INSERT INTO race(name)
VALUES
    ('HUMAN'),
    ('DWARF'),
    ('ELF'),
    ('GIANT'),
    ('ORC'),
    ('TROLL'),
    ('HOBBIT');

Create table profession
(
    id bigint auto_increment PRIMARY KEY,
    name VARCHAR
);

INSERT INTO profession(name)
VALUES ('WARRIOR'),
       ('ROGUE'),
       ('SORCERER'),
       ('CLERIC'),
       ('PALADIN'),
       ('NAZGUL'),
       ('WARLOCK'),
       ('DRUID');

CREATE TABLE player
(
    id              auto_increment PRIMARY KEY,
    name           VARCHAR(12) NOT NULL,
    title          VARCHAR(30) NULL,
    race_id        INTEGER REFERENCES race(id),
    experience     INTEGER     CHECK ( 0 < experience AND experience <= 10000000 ),
    profession_id     INTEGER REFERENCES profession(id),
    level          INTEGER check ( 0 <= level ) NOT NULL,
    untilnextlevel INTEGER NOT NULL,
    birthday       TIMESTAMP NOT NULL,
    banned         BOOLEAN NOT NULL
);

INSERT INTO player(name, title, race_id, profession_id, birthday, banned, experience, level, untilnextlevel)
VALUES ('Ниус', 'Приходящий Без Шума', (SELECT ID FROM RACE WHERE NAME = 'HOBBIT'), (SELECT ID FROM PROFESSION WHERE NAME = 'ROGUE'), '2010-10-12', false, 58347, 33, 1153)
     , ('Никрашш', 'НайтВульф', (SELECT ID FROM RACE WHERE NAME = 'ORC'), (SELECT ID FROM PROFESSION WHERE NAME = 'WARLOCK'), '2010-02-14', false, 174403, 58, 2597)
     , ('Эззэссэль', 'шипящая', (SELECT ID FROM RACE WHERE NAME = 'DWARF'), (SELECT ID FROM PROFESSION WHERE NAME = 'CLERIC'), '2006-02-28', true, 804, 3, 196)
     , ('Бэлан', 'Тсе Раа', (SELECT ID FROM RACE WHERE NAME = 'DWARF'), (SELECT ID FROM PROFESSION WHERE NAME = 'ROGUE'), '2008-02-25', true, 44553, 29, 1947)
     , ('Элеонора', 'Бабушка', (SELECT ID FROM RACE WHERE NAME = 'HUMAN'), (SELECT ID FROM PROFESSION WHERE NAME = 'SORCERER'), '2006-01-07', true, 63986, 35, 2614)
     , ('Эман', 'Ухастый Летун', (SELECT ID FROM RACE WHERE NAME = 'ELF'), (SELECT ID FROM PROFESSION WHERE NAME = 'SORCERER'), '2004-06-21', false, 163743, 56, 1557)
     , ('Талан', 'Рожденный в Бронксе', (SELECT ID FROM RACE WHERE NAME = 'GIANT'), (SELECT ID FROM PROFESSION WHERE NAME = 'ROGUE'), '2005-05-15', false, 68950, 36, 1350)
     , ('Арилан', 'Благотворитель', (SELECT ID FROM RACE WHERE NAME = 'ELF'), (SELECT ID FROM PROFESSION WHERE NAME = 'SORCERER'), '2006-08-10', false, 61023, 34, 1977)
     , ('Деракт', 'Эльфёнок Красное Ухо',(SELECT ID FROM RACE WHERE NAME = 'ELF'), (SELECT ID FROM PROFESSION WHERE NAME = 'ROGUE'), '2010-06-22', false, 156630, 55, 2970)
     , ('Архилл', 'Смертоносный', (SELECT ID FROM RACE WHERE NAME = 'GIANT'), (SELECT ID FROM PROFESSION WHERE NAME = 'PALADIN'), '2005-01-12', false, 76010, 38, 1990)
     , ('Эндарион', 'Маленький эльфенок', (SELECT ID FROM RACE WHERE NAME = 'ELF'), (SELECT ID FROM PROFESSION WHERE NAME = 'DRUID'), '2001-04-24', false, 103734, 45, 4366)
     , ('Фаэрвин', 'Темный Идеолог', (SELECT ID FROM RACE WHERE NAME = 'HUMAN'), (SELECT ID FROM PROFESSION WHERE NAME = 'NAZGUL'), '2010-09-06', false, 7903, 12, 1197)
     , ('Харидин', 'Бедуин', (SELECT ID FROM RACE WHERE NAME = 'TROLL'), (SELECT ID FROM PROFESSION WHERE NAME = 'WARRIOR'), '2009-09-08', false, 114088, 47, 3512)
     , ('Джур', 'БоРец с жАжДой', (SELECT ID FROM RACE WHERE NAME = 'ORC'), (SELECT ID FROM PROFESSION WHERE NAME = 'DRUID'), '2009-07-14', false, 29573, 23, 427)
     , ('Грон', 'Воин обреченный на бой', (SELECT ID FROM RACE WHERE NAME = 'GIANT'), (SELECT ID FROM PROFESSION WHERE NAME = 'PALADIN'), '2005-04-28', false, 174414, 58, 2586)
     , ('Морвиел', 'Копье Калимы', (SELECT ID FROM RACE WHERE NAME = 'ELF'), (SELECT ID FROM PROFESSION WHERE NAME = 'CLERIC'), '2010-03-15', false, 49872, 31, 2928)
     , ('Ннуфис', 'ДиамантоваЯ', (SELECT ID FROM RACE WHERE NAME = 'HUMAN'), (SELECT ID FROM PROFESSION WHERE NAME = 'ROGUE'), '2001-09-03', false, 162477, 56, 2823)
     , ('Ырх', 'Троль гнет ель', (SELECT ID FROM RACE WHERE NAME = 'TROLL'), (SELECT ID FROM PROFESSION WHERE NAME = 'WARRIOR'), '2001-04-08', true, 136860, 51, 940)
     , ('Блэйк', 'Серый Воин', (SELECT ID FROM RACE WHERE NAME = 'HUMAN'), (SELECT ID FROM PROFESSION WHERE NAME = 'ROGUE'), '2005-05-23', false, 151039, 54, 2961)
     , ('Нэсс', 'Бусинка', (SELECT ID FROM RACE WHERE NAME = 'TROLL'), (SELECT ID FROM PROFESSION WHERE NAME = 'WARRIOR'), '2008-02-09', true, 64945, 35, 1655)
     , ('Ферин', 'Воитель', (SELECT ID FROM RACE WHERE NAME = 'TROLL'), (SELECT ID FROM PROFESSION WHERE NAME = 'WARRIOR'), '2003-07-08', false, 120006, 48, 2494)
     , ('Солках', 'Ученик Магии', (SELECT ID FROM RACE WHERE NAME = 'ELF'), (SELECT ID FROM PROFESSION WHERE NAME = 'SORCERER'), '2001-11-07', false, 152996, 54, 1004)
     , ('Сцинк', 'Титан Войны',(SELECT ID FROM RACE WHERE NAME = 'GIANT'), (SELECT ID FROM PROFESSION WHERE NAME = 'WARRIOR'), '2008-01-04', true, 86585, 41, 3715)
     , ('Айша', 'Искусительница', (SELECT ID FROM RACE WHERE NAME = 'HUMAN'), (SELECT ID FROM PROFESSION WHERE NAME = 'CLERIC'), '2010-01-25', false, 106181, 45, 1919)
     , ('Тант', 'Черт закAтай вату', (SELECT ID FROM RACE WHERE NAME = 'DWARF'), (SELECT ID FROM PROFESSION WHERE NAME = 'PALADIN'), '2010-10-03', false, 33889, 25, 1211)
     , ('Трениган', 'Великий Волшебник', (SELECT ID FROM RACE WHERE NAME = 'ELF'), (SELECT ID FROM PROFESSION WHERE NAME = 'SORCERER'), '2004-05-17', false, 91676, 42, 2924)
     , ('Вуджер', 'Печальный',(SELECT ID FROM RACE WHERE NAME = 'TROLL'), (SELECT ID FROM PROFESSION WHERE NAME = 'NAZGUL'), '2010-10-04', false, 93079, 42, 1521)
     , ('Камираж', 'БAнкир', (SELECT ID FROM RACE WHERE NAME = 'DWARF'), (SELECT ID FROM PROFESSION WHERE NAME = 'CLERIC'), '2005-08-05', true, 79884, 39, 2116)
     , ('Ларкин', 'СвЯтой', (SELECT ID FROM RACE WHERE NAME = 'HOBBIT'), (SELECT ID FROM PROFESSION WHERE NAME = 'CLERIC'), '2003-07-10', false, 111868, 46, 932)
     , ('Зандир', 'Темновидец', (SELECT ID FROM RACE WHERE NAME = 'ELF'), (SELECT ID FROM PROFESSION WHERE NAME = 'WARLOCK'), '2003-05-24', false, 29654, 23, 346)
     , ('Балгор', 'пещерный Урук', (SELECT ID FROM RACE WHERE NAME = 'ORC'), (SELECT ID FROM PROFESSION WHERE NAME = 'NAZGUL'), '2005-02-23', false, 18869, 18, 131)
     , ('Регарн', 'Любитель ОЛивье', (SELECT ID FROM RACE WHERE NAME = 'GIANT'), (SELECT ID FROM PROFESSION WHERE NAME = 'WARRIOR'), '2006-12-23', false, 144878, 53, 3622)
     , ('Анжелли', 'Молодой Боец', (SELECT ID FROM RACE WHERE NAME = 'DWARF'), (SELECT ID FROM PROFESSION WHERE NAME = 'WARRIOR'), '2010-04-08', false, 59281, 33, 219)
     , ('Джерис', 'Имперский Воин', (SELECT ID FROM RACE WHERE NAME = 'ORC'), (SELECT ID FROM PROFESSION WHERE NAME = 'WARRIOR'), '2001-05-12', false, 173807, 58, 3193)
     , ('Жэкс', 'Ярочкино Солнышко', (SELECT ID FROM RACE WHERE NAME = 'GIANT'), (SELECT ID FROM PROFESSION WHERE NAME = 'WARRIOR'), '2008-01-04', false, 848, 3, 152)
     , ('Филуэль', 'Химик и Карпускулярник.', (SELECT ID FROM RACE WHERE NAME = 'ELF'), (SELECT ID FROM PROFESSION WHERE NAME = 'WARLOCK'), '2008-08-03', false, 48496, 30, 1104)
     , ('Яра', 'Прельстивая', (SELECT ID FROM RACE WHERE NAME = 'HUMAN'), (SELECT ID FROM PROFESSION WHERE NAME = 'CLERIC'), '2004-06-12', false, 138306, 52, 4794)
     , ('Иллинас', 'Иероглиф', (SELECT ID FROM RACE WHERE NAME = 'HOBBIT'), (SELECT ID FROM PROFESSION WHERE NAME = 'WARRIOR'), '2007-06-03', false, 115546, 47, 2054)
     , ('Ардонг', 'Вспышк A', (SELECT ID FROM RACE WHERE NAME = 'HUMAN'), (SELECT ID FROM PROFESSION WHERE NAME = 'WARLOCK'), '2009-09-16', false, 24984, 21, 316)
     , ('Аттирис', 'и.о.Карвандоса', (SELECT ID FROM RACE WHERE NAME = 'ELF'), (SELECT ID FROM PROFESSION WHERE NAME = 'SORCERER'), '2010-04-15', true, 60520, 34, 2480);


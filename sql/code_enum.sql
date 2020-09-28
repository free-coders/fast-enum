

CREATE TABLE fenum.code_enum
(
    code_id         varchar(11)                      NOT NULL default '' COMMENT '代码编号',
    enum_id         varchar(11)                      NOT NULL NOT NULL default '' COMMENT '枚举值编号',
    enum_desc       varchar(2000) CHARACTER SET utf8 not null          default '' COMMENT '枚举值内容',
    `language`      varchar(5)                       NOT NULL NOT NULL default '' COMMENT '语种',
    status          int(1)                           NOT NULL NOT NULL default -1 COMMENT '状态， 0为禁用、1为启用',
    create_datetime datetime                         NOT NULL          DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
    mod_datetime    datetime                         NOT NULL          DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',
    PRIMARY KEY (code_id, enum_id)
)
ENGINE=InnoDB DEFAULT CHARACTER SET utf8 COMMENT = '代码枚举值表';

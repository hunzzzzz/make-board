-- 시퀀스
CREATE SEQUENCE seq_t_board_comment START WITH 1 INCREMENT BY 1 NOCACHE;
-- 시퀀스 트리거
CREATE
OR REPLACE
   TRIGGER trg_board_comment_id BEFORE INSERT ON t_board_comment FOR EACH ROW BEGIN :NEW.id := seq_t_board_comment.NEXTVAL;
END;
/ -- 테이블 생성
CREATE TABLE t_board_comment
(
   id NUMBER PRIMARY KEY,
   post_id NUMBER NOT NULL,
   user_id VARCHAR2 (36) NOT NULL,
   parent_id NUMBER,
   status VARCHAR2 (10) DEFAULT 'ACTIVE' NOT NULL,
   content CLOB NOT NULL,
   author VARCHAR2 (100) NOT NULL,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
   CONSTRAINT fk_comment_post FOREIGN KEY (post_id) REFERENCES t_board_post (id) ON DELETE CASCADE,
   CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES t_board_user (id) ON DELETE CASCADE,
   CONSTRAINT check_comment_status CHECK
   (
      status IN
      (
         'ACTIVE',
         'DELETED'
      )
   )
);
-- 트리거 생성 (updated_at)
CREATE
OR REPLACE
   TRIGGER trg_board_comment_updated_at BEFORE UPDATE ON t_board_comment FOR EACH ROW BEGIN :NEW.updated_at := SYSTIMESTAMP;
END;
/
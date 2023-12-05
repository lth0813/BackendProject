package com.example.blog.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BlogDao {
    @Autowired
    JdbcTemplate jt;
    // 전체 게시글 조회
    public List<Map<String,Object>> selectAll() {
        String sqlStmt = "SELECT * FROM boardlist ORDER BY seq DESC";
        return jt.queryForList(sqlStmt);
    }
    // 소주제 조회
    public List<Map<String,Object>> selectSmallTopic(String smallseq) {
        String sqlStmt = String.format("SELECT * FROM smalltopic WHERE smallseq = ('%s')", smallseq);
        return jt.queryForList(sqlStmt);
    }
    // 게시글 조회
    public List<Map<String,Object>> selectBoard(String seq) {
        String sqlStmt = String.format("SELECT * FROM boardlist WHERE seq = ('%s')", seq);
        return jt.queryForList(sqlStmt);
    }
    // INSERT용 최신 게시글 조회
    public List<Map<String,Object>> selectBoardForInsert() {
        String sqlStmt = "SELECT * FROM boardlist ORDER BY seq DESC LIMIT 1";
        return jt.queryForList(sqlStmt);
    }
    // 해당 게시글의 소주제 조회
    public List<Map<String,Object>> selectSTbySeq(String seq) {
        String sqlStmt = String.format("SELECT smallseq FROM boardlist WHERE seq = ('%s')", seq);
        return jt.queryForList(sqlStmt);
    }
    // 해당 게시글의 답글 조회
    public List<Map<String,Object>> selectAnswerbySeq(String seq) {
        String sqlStmt = String.format("SELECT * FROM boardanswer WHERE seq = ('%s')", seq);
        return jt.queryForList(sqlStmt);
    }
    // 해당 답글의 게시글 조회
    public List<Map<String,Object>> selectBoardbyid(String id) {
        String sqlStmt = String.format("SELECT seq FROM boardanswer WHERE id = ('%s'), id");
        return jt.queryForList(sqlStmt);
    }
    // 답글 전체 조회 최신순으로 5개
    public List<Map<String,Object>> selectAnswer() {
        String sqlStmt = String.format("SELECT * FROM boardanswer ORDER BY id DESC LIMIT 5");
        return jt.queryForList(sqlStmt);
    }
    // 공지 전체 조회
    public List<Map<String,Object>> selectNotice() {
        String sqlStmt = String.format("SELECT * FROM boardnotice ORDER BY seq DESC");
        return jt.queryForList(sqlStmt);
    }
    // 공지 조회 5개 최신순
    public List<Map<String,Object>> selectNotice5() {
        String sqlStmt = "SELECT * FROM boardnotice ORDER BY seq DESC LIMIT 5";
        return jt.queryForList(sqlStmt);
    } 
    // 공지글 조회
    public List<Map<String,Object>> boardNotice(String seq) {
        String sqlStmt = String.format("SELECT * FROM boardnotice WHERE seq = ('%s')",seq);
        return jt.queryForList(sqlStmt);
    }

    // 대주제 insert
    public void insertBigTopic(String bigtopic) {
        String sqlStmt = String.format("INSERT INTO bigtopic (bigtopic) VALUES ('%s')",bigtopic);
        jt.execute(sqlStmt);
    }
    // 소주제 insert
    public void insertSmallTopic(String smalltopic, int bigseq) {
        String sqlStmt = String.format("INSERT INTO smalltopic (smalltopic, bigseq) VALUES ('%s','%s')",smalltopic, bigseq);
        jt.execute(sqlStmt);
    }
    // 게시글 insert
    public void insertBoard(String title, String content, String smallseq, String image, String imagename) {
        String sqlStmt = String.format("INSERT INTO boardlist (title, content, date, smallseq, image, imagename) VALUES ('%s','%s',NOW(),'%s','%s','%s')",title, content, smallseq, image, imagename);
        jt.execute(sqlStmt);
    }
    // 댓글 insert
    public void insertAnswer(String seq, String content, String writer) {
        String sqlStmt = String.format("INSERT INTO boardanswer(seq, content, date, writer) VALUES ('%s','%s',NOW(), '%s')",seq,content,writer);
        jt.execute(sqlStmt);
    }
    // 공지 insert
    public void insertNotice(String title, String content) {
        String sqlStmt = String.format("INSERT INTO boardnotice (title, content, date) VALUES ('%s','%s',NOW())",title, content);
        jt.execute(sqlStmt);
    }
    
    // 대주제 delete
    public void deleteBigTopic(String bigseq) {
        String sqlStmt = String.format("DELETE FROM bigtopic WHERE bigseq = '%s'",bigseq);
        jt.execute(sqlStmt);
    }
    // 소주제 delete
    public void deleteSmallTopic(String smallseq) {
        String sqlStmt = String.format("DELETE FROM smalltopic WHERE smallseq = '%s'",smallseq);
        jt.execute(sqlStmt);
    }
    // 게시글 delete
    public void deleteBoard(String seq) {
        String sqlStmt = String.format("DELETE FROM boardlist WHERE seq = '%s'",seq);
        jt.execute(sqlStmt);
    }
    // 댓글 delete
    public void deleteAnswer(String id) {
        String sqlStmt = String.format("DELETE FROM boardanswer WHERE id = '%s'",id);
        jt.execute(sqlStmt);
    }
    // 공지 delete
    public void deleteNotice(String seq) {
        String sqlStmt = String.format("DELETE FROM boardnotice WHERE seq = '%s'",seq);
        jt.execute(sqlStmt);
    }

    // 대주제 update
    public void updateBigTopic(String bigseq, String bigtopic) {
        String sqlStmt = String.format("UPDATE bigtopic SET bigtopic='%s' WHERE bigseq = %s",bigtopic,bigseq);
        jt.execute(sqlStmt);
    }
    // 소주제 update
    public void updateSmallTopic(String smallseq, String smalltopic) {
        String sqlStmt = String.format("UPDATE smalltopic SET smalltopic='%s' WHERE smallseq = %s",smalltopic,smallseq);
        jt.execute(sqlStmt);
    }
    // 게시글 update
    public void updateBoard(String seq, String title, String content, String image, String imagename) {
        String sqlStmt = String.format("UPDATE boardlist SET title='%s', content='%s', image='%s', imagename='%s', date=NOW() WHERE seq = %s",title, content, image, imagename, seq);
        jt.execute(sqlStmt);
    }
    // 댓글 update
    public void updateAnswer(String id, String content) {
        String sqlStmt = String.format("UPDATE boardanswer SET content='%s', date=NOW() WHERE id = %s",content,id);
        jt.execute(sqlStmt);
    }
    // 공지 update
    public void updateNotice(String seq, String title, String content) {
        String sqlStmt = String.format("UPDATE boardnotice SET title='%s',content='%s', date=NOW() WHERE seq = %s",title,content,seq);
        jt.execute(sqlStmt);
    }

    // 전체 게시글 개수 확인
    public List<Map<String,Object>> countAll() {
        String sqlStmt = "SELECT COUNT(*) cnt FROM boardlist";
        return jt.queryForList(sqlStmt);
    }
    // 대주제 게시글 개수 확인
    public List<Map<String,Object>> countBigTopic(String bigseq) {
        String sqlStmt = String.format("SELECT COUNT(*) cnt FROM bigtopic b, smalltopic s, boardlist l WHERE b.bigseq = s.bigseq AND s.smallseq = l.smallseq AND b.bigseq = '%s",bigseq);
        return jt.queryForList(sqlStmt);
    }
    // 소주제 게시글 개수 확인
    public List<Map<String,Object>> countSmallTopic(String smallseq) {
        String sqlStmt = String.format("SELECT COUNT(*) cnt FROM smalltopic s, boardlist l WHERE s.smallseq = l.smallseq AND s.smallseq = %s",smallseq);
        return jt.queryForList(sqlStmt);
    }
    // 공지글 전체 개수
    public List<Map<String,Object>> countNotice() {
        String sqlStmt = String.format("SELECT COUNT(*) cnt FROM boardnotice");
        return jt.queryForList(sqlStmt);
    }
    // 댓글 개수 확인
    public List<Map<String,Object>> countAnswer(String seq) {
        String sqlStmt = String.format("SELECT COUNT(*) cnt FROM boardanswer WHERE seq = %s",seq);
        return jt.queryForList(sqlStmt);
    }

    // 게시글 조회수 update
    public void updateBoardSearchCount(String seq, int searchcount) {
        String sqlStmt = String.format("UPDATE boardlist SET search_count = %d WHERE seq = %s",searchcount,seq);
        jt.execute(sqlStmt);
    }
    // 공지 조회수 update
    public void updateBoardNoticeSearchCount(String seq, int searchcount) {
        String sqlStmt = String.format("UPDATE boardnotice SET search_count = %d WHERE seq = %s",searchcount,seq);
        jt.execute(sqlStmt);
    }
    // 회원가입
    public void register(String id, String pw, String name, String email, String phonenum) {
        String sqlStmt = String.format("INSERT INTO userlist (id, password, name, email, phonenumber) VALUES ('%s', '%s', '%s', '%s', '%s')", id, pw, name, email, phonenum);
        jt.execute(sqlStmt);
    }
    // 로그인
    public List<Map<String,Object>> login(String id, String pw) {
        String sqlStmt = String.format("SELECT * FROM userlist WHERE id = '%s' AND password = '%s'", id, pw);
        return jt.queryForList(sqlStmt);
    }
    // 가입된 회원 확인
    public List<Map<String,Object>> checkdup(String id) {
        String sqlStmt = String.format("SELECT * FROM userlist WHERE id = '%s'", id);
        return jt.queryForList(sqlStmt);
    }

    // 게시글의 댓글 개수 확인
    public List<Map<String,Object>> cntanswerbyseq(String smallseq) {
        String sqlStmt = String.format("""
            SELECT l.seq, l.title, l.date, l.search_count, COUNT(a.seq) AS cnt
            FROM boardlist l
            LEFT JOIN boardanswer a ON l.seq = a.seq
            INNER JOIN smalltopic s ON s.smallseq = l.smallseq
            WHERE s.smallseq = '%s'
            GROUP BY l.seq, l.title, l.date, l.search_count
            ORDER BY l.seq DESC
                """, smallseq);
        return jt.queryForList(sqlStmt);
    }
    // 전체 게시판 용 댓글 개수 확인
    public List<Map<String,Object>> cntanswerformain() {
        String sqlStmt = """
            SELECT l.seq, l.title, l.date, l.search_count, COUNT(a.seq) AS cnt
            FROM boardlist l
            LEFT JOIN boardanswer a ON l.seq = a.seq
            INNER JOIN smalltopic s ON s.smallseq = l.smallseq
            GROUP BY l.seq, l.title, l.date, l.search_count
            ORDER BY l.seq DESC
                """;
        return jt.queryForList(sqlStmt);
    }
    // 메인용 1~4개
    public List<Map<String,Object>> cntanswerformain4() {
        String sqlStmt = """
            SELECT l.seq, l.title, l.date, l.image, COUNT(a.seq) AS cnt
            FROM boardlist l
            LEFT JOIN boardanswer a ON l.seq = a.seq
            INNER JOIN smalltopic s ON s.smallseq = l.smallseq
            GROUP BY l.seq, l.title, l.date, l.search_count
            ORDER BY l.seq DESC
            LIMIT 4
                """;
        return jt.queryForList(sqlStmt);
    }
    // 메인용 5~8개
    public List<Map<String,Object>> cntanswerformain8() {
        String sqlStmt = """
            SELECT l.seq, l.title, l.date, l.image, COUNT(a.seq) AS cnt
            FROM boardlist l
            LEFT JOIN boardanswer a ON l.seq = a.seq
            INNER JOIN smalltopic s ON s.smallseq = l.smallseq
            GROUP BY l.seq, l.title, l.date, l.search_count
            ORDER BY l.seq DESC
            LIMIT 4 OFFSET 4
                """;
        return jt.queryForList(sqlStmt);
    }
    // 대주제 게시글 개수
    public List<Map<String,Object>> cntanswerforbt() {
        String sqlStmt = """
            SELECT b.bigtopic, b.bigseq, COUNT(l.seq) AS cnt
            FROM bigtopic b
            LEFT JOIN smalltopic s ON b.bigseq = s.bigseq
            LEFT JOIN boardlist l ON s.smallseq = l.smallseq
            GROUP BY b.bigtopic, b.bigseq;
                """;
        return jt.queryForList(sqlStmt);
    }
    // 소주제 게시글 개수
    public List<Map<String,Object>> cntanswerforst() {
        String sqlStmt = """
            SELECT s.smalltopic, s.smallseq, s.bigseq, COUNT(l.seq) AS cnt
            FROM smalltopic s
            LEFT JOIN boardlist l ON s.smallseq = l.smallseq
            GROUP BY s.smalltopic, s.smallseq, s.bigseq;
                """;
        return jt.queryForList(sqlStmt);
    }

    // ID 찾기
    public List<Map<String,Object>> findId(String email, String phonenum) {
        String sqlStmt = String.format("SELECT id FROM userlist WHERE email = '%s' AND phonenumber = '%s'", email, phonenum);
        return jt.queryForList(sqlStmt);
    }
    public List<Map<String,Object>> findIdcheck(String email, String phonenum) {
        String sqlStmt = String.format("SELECT COUNT(*) cnt FROM userlist WHERE email = '%s' AND phonenumber = '%s'", email, phonenum);
        return jt.queryForList(sqlStmt);
    }

    // Password 찾기
    public List<Map<String,Object>> findPw(String id, String email, String phonenum) {
        String sqlStmt = String.format("SELECT password FROM userlist WHERE id = '%s' AND email = '%s' AND phonenumber = '%s'",id, email, phonenum);
        return jt.queryForList(sqlStmt);
    }
    public List<Map<String,Object>> findPwcheck(String id, String email, String phonenum) {
        String sqlStmt = String.format("SELECT COUNT(*) cnt FROM userlist WHERE id = '%s' AND email = '%s' AND phonenumber = '%s'",id, email, phonenum);
        return jt.queryForList(sqlStmt);
    }
    
    
}

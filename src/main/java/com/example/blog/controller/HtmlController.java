package com.example.blog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.blog.dao.BlogDao;

@Controller
public class HtmlController {
    @Autowired
    BlogDao blogdao;
    
    // 메인 페이지
    @GetMapping("/")
    public String Home(@RequestParam(defaultValue = "1") int page, Model model) {
        List<Map<String,Object>> countall = blogdao.countAll();
        List<Map<String,Object>> countnotice = blogdao.countNotice();
        List<Map<String,Object>> cntaforbt = blogdao.cntanswerforbt();
        List<Map<String,Object>> cntaforst = blogdao.cntanswerforst();
        List<Map<String,Object>> countallanswer = blogdao.selectAnswer();
        List<Map<String,Object>> notice5 = blogdao.selectNotice5();
        List<Map<String,Object>> cntaformain = blogdao.cntanswerformain();
        List<Map<String,Object>> boardlistto4 = blogdao.cntanswerformain4();
        List<Map<String,Object>> boardlistto58 = blogdao.cntanswerformain8();
        
        int pageSize = 5;
        int totalItems = cntaformain.size();
        int totalPages = (int) Math.ceil((double)totalItems / pageSize);
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, totalItems);
        List<Map<String, Object>> paginatedPosts = cntaformain.subList(start, end);

        model.addAttribute("cntall", countall);
        model.addAttribute("countnotice", countnotice);
        model.addAttribute("cntaforbt", cntaforbt);
        model.addAttribute("cntaforst", cntaforst);
        model.addAttribute("answers", countallanswer);
        model.addAttribute("notice5",notice5);
        model.addAttribute("allboardto4", boardlistto4);
        model.addAttribute("allboard5to8", boardlistto58);
        model.addAttribute("cntaformain", cntaformain);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("paginatedPosts", paginatedPosts);

        return "html/BlogMain";
    }

    // 전체보기 게시판 리스트
    @GetMapping("/blogboardlist")
    public String BoardList(@RequestParam(defaultValue = "1") int page, Model model) {
        List<Map<String,Object>> countall = blogdao.countAll();
        List<Map<String,Object>> countnotice = blogdao.countNotice();
        List<Map<String,Object>> cntaforbt = blogdao.cntanswerforbt();
        List<Map<String,Object>> cntaforst = blogdao.cntanswerforst();
        List<Map<String,Object>> countallanswer = blogdao.selectAnswer();
        List<Map<String,Object>> boardlist = blogdao.selectAll();
        List<Map<String,Object>> cntaformain = blogdao.cntanswerformain();
        
        int pageSize = 5;
        int totalItems = cntaformain.size();
        int totalPages = (int) Math.ceil((double)totalItems / pageSize);
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, totalItems);
        List<Map<String, Object>> paginatedPosts = cntaformain.subList(start, end);

        model.addAttribute("cntall", countall);
        model.addAttribute("countnotice", countnotice);
        model.addAttribute("cntaforbt", cntaforbt);
        model.addAttribute("cntaforst", cntaforst);
        model.addAttribute("answers", countallanswer);
        model.addAttribute("allboard", boardlist);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("paginatedPosts", paginatedPosts);
        
        return "html/BlogBoardList";
    }

    // 소주제 게시판 리스트
    @GetMapping("/smallboardlist")
    public String smallBoardList(
        @RequestParam("smallseq") String smallseq,
        @RequestParam(defaultValue = "1") int page,
        Model model) {
        List<Map<String,Object>> countall = blogdao.countAll();
        List<Map<String,Object>> countnotice = blogdao.countNotice();
        List<Map<String,Object>> cntaforbt = blogdao.cntanswerforbt();
        List<Map<String,Object>> cntaforst = blogdao.cntanswerforst();
        List<Map<String,Object>> countallanswer = blogdao.selectAnswer();
        List<Map<String,Object>> smalltopic = blogdao.selectSmallTopic(smallseq);
        List<Map<String,Object>> countsmalltopic = blogdao.countSmallTopic(smallseq);
        List<Map<String,Object>> cntanswerbyseq = blogdao.cntanswerbyseq(smallseq);
        
        int pageSize = 5;
        int totalItems = cntanswerbyseq.size();
        int totalPages = (int) Math.ceil((double)totalItems / pageSize);
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, totalItems);
        List<Map<String, Object>> paginatedPosts = cntanswerbyseq.subList(start, end);

        model.addAttribute("cntall", countall);
        model.addAttribute("countnotice", countnotice);
        model.addAttribute("cntaforbt", cntaforbt);
        model.addAttribute("cntaforst", cntaforst);
        model.addAttribute("answers", countallanswer);
        model.addAttribute("smalltopic", smalltopic);
        model.addAttribute("cntsmalltopic", countsmalltopic);
        model.addAttribute("cntanswerbyseq", cntanswerbyseq);
        model.addAttribute("smallseq", smallseq);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("paginatedPosts", paginatedPosts);

        
        return "html/BlogSmallBoardList";
    }
    
    // 게시글
    @GetMapping("/blogboard")
    public String Board(@RequestParam("seq") String seq, Model model) {
        List<Map<String,Object>> countall = blogdao.countAll();
        List<Map<String,Object>> countnotice = blogdao.countNotice();
        List<Map<String,Object>> cntaforbt = blogdao.cntanswerforbt();
        List<Map<String,Object>> cntaforst = blogdao.cntanswerforst();
        List<Map<String,Object>> countallanswer = blogdao.selectAnswer();
        List<Map<String,Object>> board = blogdao.selectBoard(seq);
        List<Map<String,Object>> allanswer = blogdao.selectAnswerbySeq(seq);
        List<Map<String,Object>> countanswer = blogdao.countAnswer(seq);
        String title = blogdao.selectBoard(seq).get(0).get("title").toString();
        String content = blogdao.selectBoard(seq).get(0).get("content").toString();
        String cntanswer = countanswer.get(0).get("cnt").toString();
        int searchcount = Integer.parseInt(blogdao.selectBoard(seq).get(0).get("search_count").toString());
        searchcount += 1;
        blogdao.updateBoardSearchCount(seq, searchcount);
        
        model.addAttribute("cntall", countall);
        model.addAttribute("countnotice", countnotice);
        model.addAttribute("cntaforbt", cntaforbt);
        model.addAttribute("cntaforst", cntaforst);
        model.addAttribute("answers", countallanswer);
        model.addAttribute("board", board);
        model.addAttribute("allanswer", allanswer);
        model.addAttribute("cntanswer", cntanswer);
        model.addAttribute("seq", seq);
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        
        return "html/BlogBoard";
    }
    
    // 공지사항 게시글 리스트    
    @GetMapping("/noticeboardlist")
    public String noticeBoardList(@RequestParam(defaultValue = "1") int page, Model model) {
        List<Map<String,Object>> countall = blogdao.countAll();
        List<Map<String,Object>> countnotice = blogdao.countNotice();
        List<Map<String,Object>> cntaforbt = blogdao.cntanswerforbt();
        List<Map<String,Object>> cntaforst = blogdao.cntanswerforst();
        List<Map<String,Object>> countallanswer = blogdao.selectAnswer();
        List<Map<String,Object>> notice = blogdao.selectNotice();

        int pageSize = 5;
        int totalItems = notice.size();
        int totalPages = (int) Math.ceil((double)totalItems / pageSize);
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, totalItems);
        List<Map<String, Object>> paginatedPosts = notice.subList(start, end);

        model.addAttribute("cntall", countall);
        model.addAttribute("countnotice", countnotice);
        model.addAttribute("cntaforbt", cntaforbt);
        model.addAttribute("cntaforst", cntaforst);
        model.addAttribute("answers", countallanswer);
        model.addAttribute("notice", notice);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("paginatedPosts", paginatedPosts);
        
        return "html/BlogNoticeBoardList";
    }

    // 공지사항 게시글
    @GetMapping("/noticeboard")
    public String noticeBoard(@RequestParam("seq") String seq, Model model) {
        List<Map<String,Object>> countall = blogdao.countAll();
        List<Map<String,Object>> countnotice = blogdao.countNotice();
        List<Map<String,Object>> cntaforbt = blogdao.cntanswerforbt();
        List<Map<String,Object>> cntaforst = blogdao.cntanswerforst();
        List<Map<String,Object>> countallanswer = blogdao.selectAnswer();        
        List<Map<String,Object>> board = blogdao.boardNotice(seq);
        int searchcount = Integer.parseInt(blogdao.boardNotice(seq).get(0).get("search_count").toString());
        searchcount += 1;
        blogdao.updateBoardNoticeSearchCount(seq, searchcount);
        model.addAttribute("cntall", countall);
        model.addAttribute("countnotice", countnotice);
        model.addAttribute("cntaforbt", cntaforbt);
        model.addAttribute("cntaforst", cntaforst);
        model.addAttribute("answers", countallanswer);
        model.addAttribute("board", board);
        model.addAttribute("seq", seq);

        return "html/BlogNoticeBoard";
    }
    
    // 프로필 보기
    @GetMapping("/profile")
    public String profile() {
        return "html/Profile";
    }
}

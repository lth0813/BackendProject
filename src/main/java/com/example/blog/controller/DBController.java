package com.example.blog.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.blog.dao.BlogDao;

import jakarta.servlet.http.HttpSession;

@Controller
public class DBController {
    @Autowired
    BlogDao blogdao;

    // 대주제 CRUD
    @GetMapping("blogbtinsertloading")
    public String bigTopicInsert(
        @RequestParam("bigtopic") String bigtopic) {
            if (bigtopic.isEmpty()) {
                bigtopic = "(내용 없음)";
            }     
            blogdao.insertBigTopic(bigtopic);
            return "html/BigTopicInsertLoading";
    }
    
    @GetMapping("blogbtupdateloading")
    public String bigTopicUpdate(
        @RequestParam("bigseq") String bigseq,
        @RequestParam("bigtopic") String bigtopic) {
            if (bigtopic.isEmpty()) {
                bigtopic = "(내용 없음)";
            }
            blogdao.updateBigTopic(bigseq, bigtopic);
            return "html/BigTopicUpdateLoading";
    }
    
    @GetMapping("blogbtdeleteloading")
    public String bigTopicDelete(
        @RequestParam("bigseq") String bigseq) {
            blogdao.deleteBigTopic(bigseq);
            return "html/BigTopicDeleteLoading";
    }
    
    // 소주제 CRUD
    @GetMapping("blogstinsertloading")
    public String smallTopicInsert(
        @RequestParam("smalltopic") String smalltopic,
        @RequestParam("bigseq") int bigseq) {
            if (smalltopic.isEmpty()) {
                smalltopic = "(내용 없음)";
            }
            blogdao.insertSmallTopic(smalltopic, bigseq);
            return "html/SmallTopicInsertLoading";
    }

    @GetMapping("blogstupdateloading")
    public String smallTopicUpdate(
        @RequestParam("smallseq") String smallseq,
        @RequestParam("smalltopic") String smalltopic) {
            if (smalltopic.isEmpty()) {
                smalltopic = "(내용 없음)";
            }
            blogdao.updateSmallTopic(smallseq, smalltopic);
            return "html/SmallTopicUpdateLoading";
    }

    @GetMapping("blogstdeleteloading")
    public String smallTopicDelete(
        @RequestParam("smallseq") String smallseq) {
            blogdao.deleteSmallTopic(smallseq);
            return "html/SmallTopicUpdateLoading";
    }
    
    // 게시글 CRUD
    @GetMapping("boardinsert")
    public String boardInsert() { 
        return "html/BlogBoardInsertConfirm";
    }

    @PostMapping("boardinsert")
    public String boardInsert(
        @RequestParam("smallseq") String smallseq,
        @RequestParam("title") String title,
        @RequestParam("content") String content,
        @RequestParam("file") MultipartFile file,
        Model model
    ) {
        List<Map<String,Object>> countall = blogdao.countAll();
        List<Map<String,Object>> countallanswer = blogdao.selectAnswer();
        List<Map<String,Object>> countnotice = blogdao.countNotice();
        List<Map<String,Object>> cntaforbt = blogdao.cntanswerforbt();
        List<Map<String,Object>> cntaforst = blogdao.cntanswerforst();
         
        model.addAttribute("cntall", countall);
        model.addAttribute("answers", countallanswer);
        model.addAttribute("countnotice", countnotice);
        model.addAttribute("cntaforbt", cntaforbt);
        model.addAttribute("cntaforst", cntaforst);
        model.addAttribute("smallseq", smallseq);
         
        if (title.isEmpty()) {
            title = "(제목 없음)";
        }
        if (content.isEmpty()) {
            content = "(내용 없음)";
        }

        String imagename = file.getOriginalFilename();
        String image = "/images/"+imagename;
        String savefolder = "C:/Kepco A/blog_lth/src/main/resources/static/images/";
        if (imagename == "") {
            image = "/images/CATANDDOG.jpg";
        }
        File saveImage = new File(savefolder+imagename);
        try {
            blogdao.insertBoard(title, content, smallseq, image, imagename);
            file.transferTo(saveImage);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        int page = Integer.parseInt(blogdao.selectBoardForInsert().get(0).get("seq").toString());
        model.addAttribute("page", page);
        return "html/BlogBoardInsertConfirm";
    }

    @GetMapping("boardupdate")
    public String boardUpdate() {
        return "html/BlogBoardUpdateConfirm";
    }

    @PostMapping("boardupdate")
    public String boardUpdate(
        @RequestParam("seq") String seq,
        @RequestParam("title") String title,
        @RequestParam("content") String content,
        @RequestParam("imagename") String imagename,
        @RequestParam("file") MultipartFile file,
        Model model
    ) {
        List<Map<String,Object>> countall = blogdao.countAll();
        List<Map<String,Object>> countallanswer = blogdao.selectAnswer();
        List<Map<String,Object>> countnotice = blogdao.countNotice();
        List<Map<String,Object>> cntaforbt = blogdao.cntanswerforbt();
        List<Map<String,Object>> cntaforst = blogdao.cntanswerforst();

        model.addAttribute("cntall", countall);
        model.addAttribute("answers", countallanswer);
        model.addAttribute("countnotice", countnotice);
        model.addAttribute("cntaforbt", cntaforbt);
        model.addAttribute("cntaforst", cntaforst);
        model.addAttribute("seq", seq);
        
        if (title.isEmpty()) {
            title = "(제목 없음)";
        }
        if (content.isEmpty()) {
            content = "(내용 없음)";
        }
        
        String savefolder = "C:/Kepco A/blog_lth/src/main/resources/static/images/";
        // 이미 이미지가 있고 그 이미지를 바꾸지 않을때
        if (!imagename.isEmpty() & file.getOriginalFilename().isEmpty()) {
            String image = "/images/"+imagename;
            blogdao.updateBoard(seq,title,content,image,imagename);
        // 이미 이미지가 있고 그 이미지를 새로 바꿀때
        } else if (!imagename.isEmpty() & !file.getOriginalFilename().isEmpty()) {
            imagename = file.getOriginalFilename();
            String image = "/images/"+imagename;
            File saveImage = new File(savefolder+imagename);
            try {
                blogdao.updateBoard(seq,title,content,image,imagename);
                file.transferTo(saveImage);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        // 원래 이미지가 없고 이미지를 새로 추가할때
        } else if (imagename.isEmpty() & !file.getOriginalFilename().isEmpty()) {
            imagename = file.getOriginalFilename();
            String image = "/images/"+imagename;
            File saveImage = new File(savefolder+imagename);
            try {
                blogdao.updateBoard(seq,title,content,image,imagename);
                file.transferTo(saveImage);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        // 원래 이미지가 없고 새로 넣지도 않을 때
        } else if (imagename.isEmpty() & file.getOriginalFilename().isEmpty()) {
            String image = "/images/CATANDDOG.jpg";
            blogdao.updateBoard(seq,title,content,image,imagename);
        }
       
        return "html/BlogBoardUpdateConfirm";
    }

    @GetMapping("boarddelete")
    public String boardDelete(
        @RequestParam("seq") String seq,
        Model model
    ) {
        List<Map<String,Object>> countall = blogdao.countAll();
        List<Map<String,Object>> countallanswer = blogdao.selectAnswer();
        List<Map<String,Object>> countnotice = blogdao.countNotice();
        List<Map<String,Object>> cntaforbt = blogdao.cntanswerforbt();
        List<Map<String,Object>> cntaforst = blogdao.cntanswerforst();
        List<Map<String,Object>> st = blogdao.selectSTbySeq(seq);
        String smallseq = st.get(0).get("smallseq").toString();
        
        model.addAttribute("cntall", countall);
        model.addAttribute("answers", countallanswer);
        model.addAttribute("countnotice", countnotice);
        model.addAttribute("cntaforbt", cntaforbt);
        model.addAttribute("cntaforst", cntaforst);
        model.addAttribute("smallseq", smallseq);

        blogdao.deleteBoard(seq);
        return "html/BlogBoardDeleteConfirm";
    }

    // 답글 CRUD
    @PostMapping("boardanswerinsert")
    public String answerInsert(
        @RequestParam("seq") String seq,
        @RequestParam("content") String content,
        @RequestParam("writer") String writer
    ) {
        if (content.isEmpty()) {
            content = "(내용 없음)";
        }
        blogdao.insertAnswer(seq, content, writer);
        return String.format("redirect:/blogboard?seq=%s",seq);
    }

    @PostMapping("boardanswerupdate")
    public String answerUpdate(
        @RequestParam("id") String id,
        @RequestParam("content") String content,
        @RequestParam("seq") String seq
    ) {
        if (content.isEmpty()) {
            content = "(내용 없음)";
        }
        blogdao.updateAnswer(id, content);
        return String.format("redirect:/blogboard?seq=%s",seq);
    }

    @GetMapping("boardanswerdelete")
    public String answerDelete(
        @RequestParam("id") String id,
        @RequestParam("seq") String seq
    ) {
        blogdao.deleteAnswer(id);
        return String.format("redirect:/blogboard?seq=%s",seq);
    }
    
    // 공지사항 CRUD
    @PostMapping("noticeboardinsert")
        public String noticeInsert(
            @RequestParam("title") String title,
            @RequestParam("content") String content
        ) {
            if (title.isEmpty()) {
            title = "(제목 없음)";
            }
            if (content.isEmpty()) {
                content = "(내용 없음)";
            }
            blogdao.insertNotice(title, content);
            return String.format("redirect:/noticeboardlist");
        }
    
    @PostMapping("noticeboardupdate")
        public String noticeUpdate(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("seq") String seq
        ) {
            if (title.isEmpty()) {
            title = "(제목 없음)";
            }
            if (content.isEmpty()) {
                content = "(내용 없음)";
            }
            blogdao.updateNotice(seq, title, content);
            return String.format("redirect:/noticeboardlist?seq=%s",seq);
        }
    
    @GetMapping("noticeboarddelete")
    public String noticeDelete(
        @RequestParam("seq") String seq
    ) {
        blogdao.deleteNotice(seq);
        return String.format("redirect:/noticeboardlist");
    }

    // 로그인
    @GetMapping("login")
    public String login() {
        return "html/Login";
    }
    
    @PostMapping("login")
    public String loginPost(
        @RequestParam("id") String id,
        @RequestParam("pw") String pw,
        HttpSession session) {
        int check = blogdao.login(id, pw).size();
        System.out.println(check);
        if (check < 1) {
            return "html/LoginFail";
        } else {
            Map<String,Object> user = blogdao.login(id, pw).get(0);
            session.setAttribute("user", user);
            return "redirect:/loginclose";
        }
    }

    @GetMapping("loginclose")
    public String loginclose() {
        return "html/LoginClose";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // 회원가입
    @GetMapping("register")
    public String register() {
        return "html/Register";
    }
    
    @PostMapping("register")
    public String registerPost(
        @RequestParam("id") String id,
        @RequestParam("pw") String pw,
        @RequestParam("name") String name,
        @RequestParam("email") String email,
        @RequestParam("phonenum") String phonenum
    ) {
        int check = blogdao.checkdup(id).size();
            if (check > 0) {
                return "html/RegisterFail";
            } else {
                blogdao.register(id, pw, name, email, phonenum);
        return "html/RegisterConfirm";
            }
    }

    // ID 찾기
    @GetMapping("findid")
    public String findId() {
        return "html/findId";
    }
    @PostMapping("findid")
    public String findIdPost(
        @RequestParam("email") String email,
        @RequestParam("phonenum") String phonenum,
        Model model
    ) {
        if (email.isEmpty() | phonenum.isEmpty()) {
            return "redirect:/findidcheck?id=noid&"+String.format("email=%s&phonenum=%s",email,phonenum);
        }
        int check = Integer.parseInt(blogdao.findIdcheck(email, phonenum).get(0).get("cnt").toString());
        if (check < 1) {
            return "redirect:/findidcheck?id=noid&"+String.format("email=%s&phonenum=%s",email,phonenum);
        } else {
            return "redirect:/findidcheck?id=yesid&"+String.format("email=%s&phonenum=%s",email,phonenum);
        }
    }
    @GetMapping("findidcheck")
    public String findIdCheck(
        @RequestParam("email") String email,
        @RequestParam("phonenum") String phonenum,
        @RequestParam("id") String idcheck,
        Model model
    ) {
        model.addAttribute("idcheck", idcheck);
        if (!email.isEmpty() | !phonenum.isEmpty()) {
            int check = Integer.parseInt(blogdao.findIdcheck(email, phonenum).get(0).get("cnt").toString());
            if (check == 1) {
                String id = blogdao.findId(email, phonenum).get(0).get("id").toString();
                model.addAttribute("id", id);
            }
        }
        return "html/Findidcheck";
    }

    // 비밀번호 찾기
    @GetMapping("findpw")
    public String findPw() {
        return "html/findPW";
    }
    @PostMapping("findpw")
    public String findPwPost(
        @RequestParam("id") String id,
        @RequestParam("email") String email,
        @RequestParam("phonenum") String phonenum,
        Model model
    ) {
        if (id.isEmpty() | email.isEmpty() | phonenum.isEmpty()) {
            return "redirect:/findpwcheck?pw=no&"+String.format("id=%s&email=%s&phonenum=%s",id,email,phonenum);
        }
        int check = Integer.parseInt(blogdao.findPwcheck(id, email, phonenum).get(0).get("cnt").toString());
        if (check < 1) {
            return "redirect:/findpwcheck?pw=no&"+String.format("id=%s&email=%s&phonenum=%s",id,email,phonenum);
        } else {
            return "redirect:/findpwcheck?pw=yes&"+String.format("id=%s&email=%s&phonenum=%s",id,email,phonenum);
        }
    }
    @GetMapping("findpwcheck")
    public String findPwCheck(
        @RequestParam("email") String email,
        @RequestParam("phonenum") String phonenum,
        @RequestParam("id") String id,
        @RequestParam("pw") String pwcheck,
        Model model
    ) {
        model.addAttribute("pwcheck", pwcheck);
        if (!id.isEmpty() | !email.isEmpty() | !phonenum.isEmpty()) {
            int check = Integer.parseInt(blogdao.findIdcheck(email, phonenum).get(0).get("cnt").toString());
            if (check == 1) {
                String pw = blogdao.findPw(id, email, phonenum).get(0).get("password").toString();
                model.addAttribute("pw", pw);
            }
        }
        return "html/FindPWcheck";
    }
}

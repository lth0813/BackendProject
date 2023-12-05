window.addEventListener("DOMContentLoaded", () => {
    // 대주제 클릭 시 소주제 리스트 출력
    const bigtopics = document.querySelectorAll(".sidebarList-bigList")
    bigtopics.forEach((bigtopic) => {
        bigtopictitle = bigtopic.querySelectorAll(".bigtopic")
        bigtopictitle.forEach((bttitle) => {
            bttitle.addEventListener("click",() => {
                bttitle.style.fontWeight = "bold";
                const smalltopics = bigtopic.querySelectorAll(".sidebarList-smallList")
                    smalltopics.forEach((smalltopic) => {
                        if (smalltopic.style.display === "flex") {
                            smalltopic.style.display = "none"
                            bttitle.style.fontWeight = "normal";
                            smalltopic.style.fontWeight = "normal";
                        } else {
                            smalltopic.style.display = "flex"
                            smalltopic.style.fontWeight = "normal";
                            }
                        })
                if (bigtopic.querySelectorAll(".sidebarList-smallListInsert") != null) {
                    const smalltopicinsertbtn = bigtopic.querySelectorAll(".sidebarList-smallListInsert")
                    smalltopicinsertbtn.forEach((sbtns) => {
                        if (sbtns.style.display === "flex") {
                            sbtns.style.display = "none"
                            sbtns.style.fontWeight = "normal";
                        } else {
                            sbtns.style.display = "flex"
                            sbtns.style.fontWeight = "normal";
                        }
                    })
                }
            })
        })
    })
    // 대주제 추가 입력창 출력
    if (document.querySelector(".sidebarList-bigListInsert") != null) {
        const insertbigtopicbtn = document.querySelector(".sidebarList-bigListInsert")
        const insertbigtopic = document.querySelector(".insertbigtopic-wrapper")
        let bticnt = 1;
        insertbigtopicbtn.addEventListener("click",() => {
            if (bticnt == 1) {
            insertbigtopic.style.display = "flex";
            bticnt = bticnt - 1;
            } else if (bticnt == 0) {
                insertbigtopic.style.display = "none";
                bticnt = bticnt + 1;
            }
        })
    }

    // 대주제 업데이트창 출력
    const updatebigtopicbtn = document.querySelectorAll(".sidebarList-bigListUpdate")
    const updatebigtopics = document.querySelectorAll(".updatebigtopic-wrapper")
    const updatetitles = document.querySelectorAll(".bigtopic")
    for (let i = 0; i < updatebigtopicbtn.length; i++) {
        updatebigtopicbtn[i].addEventListener("click",() => {
            if (updatebigtopics[i].style.display == "flex") {
                updatebigtopics[i].style.display = "none";
                updatetitles[i].style.display = "flex";
            } else {
                updatebigtopics[i].style.display = "flex";
                updatetitles[i].style.display = "none";
            }
            
        })
    }
    // 소주제 추가 입력창 출력
    const insertsmalltopicbtn = document.querySelectorAll(".sidebarList-smallListInsert")
    const insertsmalltopic = document.querySelectorAll(".insertsmalltopic-wrapper")
    for (let i = 0; i < insertsmalltopicbtn.length; i++) {
        insertsmalltopicbtn[i].addEventListener("click",() => {
            if (insertsmalltopic[i].style.display == "flex") {
            insertsmalltopic[i].style.display = "none";
            } else {
                insertsmalltopic[i].style.display = "flex";
            }
        })
    }
    // 소주제 업데이트창 출력
    const updatesmalltopicbtn = document.querySelectorAll(".sidebarList-smallListUpdate")
    const updatesmalltopics = document.querySelectorAll(".updatesmalltopic-wrapper")
    const updatesmalltitle = document.querySelectorAll(".smalltopic")
    const updatesmalllist = document.querySelectorAll(".sidebarList-smallList")
    for (let i = 0; i < updatesmalltopicbtn.length; i++) {
        updatesmalltopicbtn[i].addEventListener("click",() => {
            if (updatesmalltopics[i].style.display == "flex") {
                updatesmalltopics[i].style.display = "none";
                updatesmalllist[i].style.display = "flex";
            } else {
                updatesmalltopics[i].style.display = "flex";
                updatesmalllist[i].style.display = "none";
            }
            
        })
    }    

    // 게시물 및 업데이트, 댓글
    // 수정 버튼 눌렀을 시 업데이트 창 출력
    const updatebtn = document.querySelector(".boardContent-update")
    const answerinsertscreen = document.querySelector(".answerInsert-wrapper")
    const answerscreen = document.querySelector(".answerList-wrapper")
    const boardcontent = document.querySelector(".boardContent-wrapper")
    const updatescreen = document.querySelector(".boardUpdate-wrapper")
    if (updatebtn != null) {
        updatebtn.addEventListener("click",() => {
            answerinsertscreen.style.display = "none";
            answerscreen.style.display = "none";
            boardcontent.style.display = "none";
            updatescreen.style.display = "flex";
            
        })
    }
    // 업데이트 창에서 취소 눌렀을때
    const updatecancelbtn = document.querySelector(".updatecancelbutton")
    if (updatecancelbtn != null) {
        updatecancelbtn.addEventListener("click",() => {
            location.reload();
        })
    }
    // 답글 달기 버튼 눌렀을 시 답글 인서트 창 출력
    const answerinsertbtn = document.querySelector(".answerreplybutton")
    if (answerinsertbtn != null) {
        answerinsertbtn.addEventListener("click",() => {
            answerinsertscreen.style.display = "flex";
        })
    }
    // 답글 인서트 창 취소
    const answerinsertcancel = document.querySelector(".answerinsertcancel")
    if (answerinsertcancel != null) {
        answerinsertcancel.addEventListener("click",() => {
            location.reload();
        })
    }
    // 답글 수정 버튼 눌렀을 시 답글 업데이트 창 출력
    const answerupdatebtn = document.querySelectorAll(".answerList-updatebtn")
    const answerdeletebtn = document.querySelectorAll(".answerList-deletebtn")
    const answers = document.querySelectorAll(".answerList-answer")
    const answerupdatescreen = document.querySelectorAll(".answerUpdate-wrapper")
    if (answerupdatebtn != null) {
        for (let i = 0; i < answerupdatebtn.length; i++) {
            answerupdatebtn[i].addEventListener("click",() => {
                answers[i].style.display = "none";
                answerupdatebtn[i].style.display = "none";
                answerdeletebtn[i].style.display = "none";
                answerupdatescreen[i].style.display = "flex";
            })
        }
    }
    // 답글 업데이트 창 취소
    const answerupdatecancel = document.querySelectorAll(".answerupdatecancel")
    if (answerupdatecancel != null) {
        for (let i = 0; i < answerupdatecancel.length; i++) {
            answerupdatecancel[i].addEventListener("click",() => {
                answers[i].style.display = "flex";
                answerupdatebtn[i].style.display = "flex";
                answerdeletebtn[i].style.display = "flex";
                answerupdatescreen[i].style.display = "none";
            })
        }
    }
})
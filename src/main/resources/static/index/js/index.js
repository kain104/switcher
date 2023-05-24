const nickname = sessionStorage.getItem('nickname');
new Vue({
    el: '.login',
    data() {
        return {
            isLoggedIn: false,
            nickname: ''
        };
    },
    created() {
        if (nickname){
            this.isLoggedIn = true;
            this.nickname = nickname;
        }else {
            this.isLoggedIn = false;
        }
    }
});
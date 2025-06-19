const Navbar = {
  template: `
    <header class="navbar-container">
      <nav class="navbar-nav">
        <ul class="nav-links">
          <li><a href="/posts" class="nav-link">자유게시판</a></li>
          <li><a href="/temp1" class="nav-link">임시1</a></li>
          <li><a href="/temp2" class="nav-link">임시2</a></li>
          <li><a href="/temp3" class="nav-link">임시3</a></li>
          <li><a href="/temp4" class="nav-link">임시4</a></li>
        </ul>
        <div class="auth-buttons">
          <template v-if="isLoggedIn">
            <button @click="logout" class="auth-button logout-button">로그아웃</button>
            <span class="user-info">{{ currentUser }}님</span>
          </template>
          <template v-else>
            <a href="/signup" class="auth-button signup-button">회원가입</a>
            <a href="/login" class="auth-button login-button">로그인</a>
          </template>
        </div>
      </nav>
    </header>
  `,
  setup() {
    const { ref } = Vue;

    const isLoggedIn = ref(false);
    const currentUser = ref('Guest');

    const checkLoginStatus = () => {
      const token = localStorage.getItem('authToken');
      if (token === 'my_super_secret_token') {
        isLoggedIn.value = true;
        currentUser.value = localStorage.getItem('username') || '사용자';
      } else {
        isLoggedIn.value = false;
        currentUser.value = 'Guest';
      }
    };

    const logout = () => {
      if (confirm('로그아웃 하시겠습니까?')) {
        localStorage.removeItem('authToken');
        localStorage.removeItem('username');
        isLoggedIn.value = false;
        currentUser.value = 'Guest';
        alert('로그아웃 되었습니다.');
      }
    };

    Vue.onMounted(checkLoginStatus);

    return {
      isLoggedIn,
      currentUser,
      logout,
    };
  }
};

export default Navbar;
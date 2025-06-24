 const campos = [
    {
      name: 'nome',
      fn: val => /^[A-Za-zÀ-ÿ\s]+$/.test(val.trim()) && val.trim().split(/\s+/).length >= 2,
      msg: 'Digite seu nome completo '
    },
    {
      name: 'email',
      fn: val => /^\S+@\S+\.\S+$/.test(val) && val === val.toLowerCase(),
      msg: 'E-mail inválido '
    },
    {
      name: 'senha',
      fn: val => val.length >= 6,
      msg: 'A senha deve ter no mínimo 6 caracteres'
    },
    {
      name: 'tipo',
      fn: val => val !== '',
      msg: 'Selecione o tipo de conta'
    }
  ];

  // Impede letras maiúsculas no e-mail
  document.querySelector('#email').addEventListener('input', e => {
    e.target.value = e.target.value.toLowerCase();
  });

  // Bloqueia números no nome
  document.querySelector('#nome').addEventListener('input', e => {
    e.target.value = e.target.value.replace(/[^A-Za-zÀ-ÿ\s]/g, '');
  });

  // Validação ao sair do campo
  campos.forEach(({ name, fn, msg }) => {
    const input = document.querySelector(`[name="${name}"]`);
    input.addEventListener('blur', () => validarCampo(input, fn, msg));
    input.addEventListener('input', () => removerErro(input));
  });
  document.getElementById('toggleSenha').addEventListener('click', () => {
  const senhaInput = document.getElementById('senha');
  const tipo = senhaInput.getAttribute('type');
  senhaInput.setAttribute('type', tipo === 'password' ? 'text' : 'password');
});

  function validarCampo(input, fn, msg) {
    const valido = fn(input.value);
    input.classList.toggle('is-invalid', !valido);
    let feedback = input.parentElement.querySelector('.invalid-feedback');

    if (!valido && !feedback) {
      feedback = document.createElement('div');
      feedback.className = 'invalid-feedback';
      feedback.innerText = msg;
      input.parentElement.appendChild(feedback);
    } else if (valido && feedback) {
      feedback.remove();
    }
    return valido;
  }

  function removerErro(input) {
    input.classList.remove('is-invalid');
    const feedback = input.parentElement.querySelector('.invalid-feedback');
    if (feedback) feedback.remove();
  }

  // Ao clicar no botão
  document.querySelector('#btnCadastrar').addEventListener('click', e => {
    e.preventDefault();

    let dados = {};
    let tudoValido = true;

    campos.forEach(({ name, fn, msg }) => {
      const input = document.querySelector(`[name="${name}"]`);
      const valido = validarCampo(input, fn, msg);
      if (!valido) tudoValido = false;
      else dados[name] = input.value.trim();
    });
    document.getElementById('btnVoltar').addEventListener('click', () => {
  window.location.href = '/templates/pagina-inicial.html';
});

    const mensagem = document.getElementById('mensagem');
    mensagem.innerHTML = '';

    if (tudoValido) {
      // Simulando cadastro (substitua aqui pelo fetch/axios do seu backend)
      mensagem.innerHTML = '<p style="color: green;">Cadastro realizado com sucesso! Redirecionando...</p>';

      setTimeout(() => {
        window.location.href = '/templates/pagina-inicial.html';
      }, 2000);
    } else {
      mensagem.innerHTML = '<p style="color: red;">Verifique os campos destacados antes de continuar.</p>';
    }
  });
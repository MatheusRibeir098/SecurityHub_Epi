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
const emailInput = document.querySelector('#email');
if (emailInput) { // Verifica se o elemento existe antes de adicionar o listener
  emailInput.addEventListener('input', e => {
    e.target.value = e.target.value.toLowerCase();
  });
}


// Bloqueia números no nome
const nomeInput = document.querySelector('#nome');
if (nomeInput) { // Verifica se o elemento existe antes de adicionar o listener
  nomeInput.addEventListener('input', e => {
    e.target.value = e.target.value.replace(/[^A-Za-zÀ-ÿ\s]/g, '');
  });
}


// Validação ao sair do campo (blur) e ao digitar (input)
campos.forEach(({ name, fn, msg }) => {
  const input = document.querySelector(`[name="${name}"]`);
  if (input) { // Verifica se o elemento existe antes de adicionar listeners
    input.addEventListener('blur', () => validarCampo(input, fn, msg));
    input.addEventListener('input', () => removerErro(input));
  }
});

// Toggle senha visibility
const toggleSenhaButton = document.getElementById('toggleSenha');
if (toggleSenhaButton) { // Verifica se o elemento existe antes de adicionar o listener
  const senhaInput = document.getElementById('senha');
  if (senhaInput) { // Verifica se o input de senha existe
    toggleSenhaButton.addEventListener('click', () => {
      const tipo = senhaInput.getAttribute('type');
      senhaInput.setAttribute('type', tipo === 'password' ? 'text' : 'password');
    });
  }
}


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

// A LÓGICA DE SUBMISSÃO DO FORMULÁRIO E REDIRECIONAMENTO VIA JAVASCRIPT FOI REMOVIDA DAQUI.
// O formulário agora é submetido diretamente pelo navegador para o Spring Boot.
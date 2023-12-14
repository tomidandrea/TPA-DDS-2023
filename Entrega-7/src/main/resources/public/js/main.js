function crearMensaje(mensaje, tipo){
    let container = document.getElementById("encabezado");
    let toast = document.createElement("div");
    toast.classList.add("toast", "align-items-center", `text-bg-${tipo}`, "bg-opacity-25",
        "show","w-100", "mt-2", "fw-semibold");
    toast.setAttribute("role","alert");
    toast.setAttribute("aria-live","assertive");
    toast.setAttribute("aria-atomic","true");
    toast.setAttribute("data-bs-autohide", "false");

    toast.innerHTML =`<div class="d-flex">
                        <div class="toast-body text-dark">
                            ${mensaje}
                        </div>
                        <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast"
                                aria-label="Close"></button>
                    </div>`;

    toast.addEventListener('hidden.bs.toast', () => {
        container.removeChild(toast)
    })

    container.appendChild(toast);
}




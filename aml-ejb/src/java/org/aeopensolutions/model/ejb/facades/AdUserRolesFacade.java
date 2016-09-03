/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aeopensolutions.model.ejb.facades;

import java.util.List;
import java.util.logging.Level;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.aeopensolutions.model.ejb.services.AdPackageService;
import org.aeopensolutions.model.entities.AdRole;
import org.aeopensolutions.model.entities.AdUser;
import org.aeopensolutions.model.entities.AdUserRoles;
import org.aeopensolutions.model.enums.Estado;
import org.aeopensolutions.model.enums.YesNo;
import org.aeopensolutions.model.exceptions.ExecuteRollbackException;
import org.aeopensolutions.model.exceptions.ExistException;
import org.aeopensolutions.model.exceptions.ProcessOperationException;

/**
 *
 * @author mllerena
 */
@Stateless
public class AdUserRolesFacade extends AbstractFacade<AdUserRoles> {

    @PersistenceContext(unitName = "aml-ejbPU")
    private EntityManager em;

    @EJB
    private AdPackageService packageService;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdUserRolesFacade() {
        super(AdUserRoles.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(AdUserRoles adUserRoles, AdUser adUser) throws ExecuteRollbackException {
        try {
            if (adUserRoles == null) {
                throw new ProcessOperationException("El parámetro usuarioRol no puede ser null.");
            }

            if (adUser == null) {
                throw new ProcessOperationException("El parámetro usuario no puede ser null.");
            }

            if (adUser.getId() == null) {
                throw new ProcessOperationException("El usuario no esta guardado, no existe en la base de datos.");
            }

            if (adUserRoles.getId() == null) {
                //usuarioRolFacade.almacenar(usuarioRol);

                adUserRoles.setAdUserId(adUser);

                /*
                if (adUserRoles.getAdRoleId().getName() != null) {
                    if (adUserRoles.getAdRoleId().getName().toUpperCase().contains("ADMIN")) {
                        adUserRoles.setIsRoleAdmin(new Character('Y'));
                    } else {
                        adUserRoles.setIsRoleAdmin(new Character('N'));
                    }
                }
                */

                validarExistencia(adUserRoles);
                this.create(adUserRoles);

            } else {
                //usuarioRolFacade.actualizar(usuarioRol);

                validarExistenciaActualizar(adUserRoles);
                this.edit(adUserRoles);
            }

        } catch (ProcessOperationException e) {
            throw new ExecuteRollbackException(e.getMessage());
        } catch (ExistException e) {
            throw new ExecuteRollbackException("El rol " + adUserRoles.getAdRoleId().getName() + " ya ha sido asignado anteriormente.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecuteRollbackException("Error al guardar el registro!");
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(List<AdUserRoles> adUserRolesList, AdUser user) throws ExecuteRollbackException {
        try {
            if (adUserRolesList == null) {
                throw new ProcessOperationException("El parámetro usuarioRol no puede ser null.");
            }

            if (user == null) {
                throw new ProcessOperationException("El parámetro usuario no puede ser null.");
            }

            if (user.getId() == null) {
                throw new ProcessOperationException("El usuario no esta guardado, no existe en la base de datos.");
            }

            for (AdUserRoles adUserRoles : adUserRolesList) {
                this.save(adUserRoles, user);
            }

        } catch (ExecuteRollbackException e) {
            throw new ExecuteRollbackException(e.getMessage());
        } catch (ProcessOperationException e) {
            throw new ExecuteRollbackException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecuteRollbackException("Error al guardar los registros!");
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(AdUserRoles adUserRoles) throws ExecuteRollbackException {
        try {
            if (adUserRoles == null) {
                throw new ProcessOperationException("El parámetro usuarioRol no puede ser null.");
            }

            adUserRoles.setIsactive(YesNo.NO);

            //validarExistenciaActualizar(adUserRoles);
            this.edit(adUserRoles);

        } catch (ProcessOperationException e) {
            throw new ExecuteRollbackException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecuteRollbackException("Error al borrar el registro!");
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(List<AdUserRoles> AdUserRolesList) throws ExecuteRollbackException {
        try {
            if (AdUserRolesList == null) {
                throw new ProcessOperationException("El parámetro usuarioRol no puede ser null.");
            }

            for (AdUserRoles adUserRoles : AdUserRolesList) {
                this.delete(adUserRoles);
            }

        } catch (ProcessOperationException e) {
            throw new ExecuteRollbackException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExecuteRollbackException("Error al guardar los registros!");
        }
    }

    /**
     * *****************************
     */
    public AdUserRoles findByAdUserAndAdRole(AdUser user, AdRole role) {
        List<AdUserRoles> result = null;
        try {
            Query query = em.createNamedQuery("AdUserRoles.findByAdUserAndAdRole");
            query.setParameter("adUserId", user);
            query.setParameter("adRoleId", role);
            query.setParameter("isactive", YesNo.SI);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.isEmpty() || result == null ? null : result.get(0);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<AdUserRoles> findByAdUser(AdUser user) {
        List<AdUserRoles> result = null;
        try {
            Query query = em.createNamedQuery("AdUserRoles.findByAdUser");
            query.setParameter("adUserId", user);
            query.setParameter("isactive", YesNo.SI);
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.isEmpty() || result == null ? null : result;
    }

    public void validarExistencia(AdUserRoles adUserRoles) throws ExistException {
        try {
            AdUserRoles temp = findByAdUserAndAdRole(adUserRoles.getAdUserId(), adUserRoles.getAdRoleId());
            if (temp != null) {
                throw new ExistException();
            }
        } catch (ExistException e) {
            throw new ExistException(e);
        }
    }

    public void validarExistenciaActualizar(AdUserRoles adUserRoles) throws ExistException {
        try {
            AdUserRoles usuarioWithNotChange = find(adUserRoles.getAdUserRolesId());
            if (usuarioWithNotChange.getAdRoleId().getId().equals(adUserRoles.getAdRoleId().getId())) {
                validarExistencia(adUserRoles);
            }
        } catch (ExistException e) {
            throw new ExistException(e);
        }
    }

}
